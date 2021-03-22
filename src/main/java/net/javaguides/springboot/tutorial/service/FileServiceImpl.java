package net.javaguides.springboot.tutorial.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import net.javaguides.springboot.tutorial.controller.AddProductController;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Random;

import javax.activation.MimetypesFileTypeMap;

@Service
public class FileServiceImpl implements FileService{

    private static final String UPLOAD_PATH = "upload";

    public String store (MultipartFile file) throws IOException, FileServiceException {
        this.initFilesystem();

        if (file.isEmpty()){
            throw new MultipartException("File is empty");

        }

        String contentType = file.getContentType();
        System.out.println(" - - - - > " + contentType);
        String fileType = null;
        String fileExtension = ".jpg";

        if (contentType.equals("image/jpeg")) {
            fileType = "jpg";
        }
        else if (contentType.equals("image/png")) {
            fileType = "jpg";
        }
        else if (contentType.equals("image/gif")) {
            fileType = "jpg";
        }
        else
            throw new FileServiceException(" < - - File is not an image - - > ");

        //InputStream inputStream = file.getInputStream();
        Path uploadLocation = Paths.get(UPLOAD_PATH);

        long epochSecond = Instant.now().getEpochSecond();
        String newFileName1 = epochSecond + "-" + file.getOriginalFilename();// + fileExtension;
        String newFileName = "";

        int placeOfDotFromTheEnd;
        for (int y = 1;; y ++){
            if (newFileName1.charAt(newFileName1.length() - y) == '.'){
                placeOfDotFromTheEnd = y;
                break;
            }
        }

        System.out.println(" - - - > miejsce kropki od konca stringa - > " + placeOfDotFromTheEnd);

        for (int x = 0; x < newFileName1.length() - placeOfDotFromTheEnd; x ++)
            newFileName += newFileName1.charAt(x);

        newFileName += fileExtension;

        System.out.println(" - - - > newFilename: - - - > " + newFileName);

        Path targetPath = uploadLocation.resolve(newFileName);

        Image image = null;

        try {
            image = ImageIO.read(file.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        int newWidth = 0;
        int newHeight = 0;
        int oldWidth = image.getWidth(null);
        int oldHeight = image.getHeight(null);

        if (oldWidth >= oldHeight) {
            newWidth = 700;
            newHeight = 700 * oldHeight / oldWidth;
        }
        else{
            newHeight = 700;
            newWidth = 700 * oldWidth / oldHeight;
        }

        Image newImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);


        int width = newImage.getWidth(null);
        int height = newImage.getHeight(null);

        BufferedImage bimage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

//         Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        bGr.drawImage(newImage, 0, 0, null);
        bGr.dispose();


//        int pixels [][] = new int[width][height];
//        Random rand = new Random();
//
//
//        //int n = rand.nextInt(50);
//
//        for (int x = 0; x < width; x++)
//            for (int y = 0; y < height; y++)
//                pixels[x][y] = bimage.getRGB(x, y) + rand.nextInt(500) - 25;
//
//        for (int x = 0; x < width; x++)
//            for (int y = 0; y < height; y++)
//                bimage.setRGB(x, y, pixels[x][y]);


        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(bimage, fileType, os);
        InputStream targetStream = new ByteArrayInputStream(os.toByteArray());

        long bytesWritten = Files.copy(targetStream, targetPath);

        if (bytesWritten <= 0)
            throw new FileServiceException("Couldn't copy file");


        Path path = Paths.get("upload/" + targetPath);

        if (Files.notExists(path))
            awsS3(newFileName, targetPath);

        return newFileName;
    }

    private void initFilesystem () throws IOException{
        Path uploadPath = Paths.get(UPLOAD_PATH);

        if (!Files.exists(uploadPath)) {
            Files.createDirectory(uploadPath);
        }
    }

    @Override
    public Resource getFileAsResource (String filePath){
        Path uploadPath = Paths.get(UPLOAD_PATH);
        Path targetFilePath = uploadPath.resolve(filePath);

        try {
            aws3Download(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Resource resource = null;

        try {
            resource = new UrlResource(targetFilePath.toUri());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // Resource resource = new UrlResource(aws3Download(filePath));


        if (resource.exists() || resource.isReadable())
            return resource;

        return null;
    }

    public void awsS3 (String filename, Path targetPath){
        Logger LOG = LoggerFactory.getLogger(AddProductController.class);

        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAZ33DCDXRQE5GHBWV",
                "jk4/YyY7M41ulGQGuXHyJ99Se3aJwjhY57QRHh+Z"
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        String bucketName = "radek0582-bucket01";

        if(s3client.doesBucketExist(bucketName)) {
            LOG.info("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            //return;
        }
        else
            s3client.createBucket(bucketName);

        System.out.printf("Ciag dalszy");

        List<Bucket> buckets = s3client.listBuckets();
        for(Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }

        s3client.putObject(
                bucketName,
                "Document/" + filename,
                new File(targetPath.toString())
        );

//        ObjectListing objectListing = s3client.listObjects(bucketName);
//        for(S3ObjectSummary os : objectListing.getObjectSummaries()) {
//            LOG.info(os.getKey());
//            System.out.printf("obiekty: " + os.getKey());
//        }


    }

    public void aws3Download (String filepath) throws IOException {
        AWSCredentials credentials = new BasicAWSCredentials(
                "AKIAZ33DCDXRQE5GHBWV",
                "jk4/YyY7M41ulGQGuXHyJ99Se3aJwjhY57QRHh+Z"
        );

        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .build();

        System.out.printf("Tutaj jestem");
        String bucketName = "radek0582-bucket01";

        S3Object s3object = s3client.getObject(bucketName, "Document/" + filepath);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        FileUtils.copyInputStreamToFile(inputStream, new File("upload/" + filepath));
    }
}
