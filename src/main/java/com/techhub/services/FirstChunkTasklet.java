package com.techhub.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import org.springframework.stereotype.Component;
import org.apache.commons.io.FileUtils;

@Component
public class FirstChunkTasklet implements Tasklet {

	@Value("${ziplocation}") 
	private String zipLocation;
	
	
	
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
      
	       File rootFolder = new File("F:\\Eclipse_Workspace\\ZipFilesBatchProcessing\\");
	       fileMovement(rootFolder);


	       return RepeatStatus.FINISHED;
		
	}
	
	
	public void fileMovement(File folder) throws IOException {
		final String allowedFileName = "inputcsvs";
	   
	    File[] fileList = folder.listFiles();

	    if (fileList == null) {
	        System.out.println("No Folder Exists");
	        return;
	    }
	    for (final File file : fileList ) {
	    	if (! file.getName().equals(allowedFileName)){
	    	for (File file1 : file.listFiles()) {
	    		if(file1.getName().endsWith("zip")) {
	    			unzip(file1.getAbsolutePath().toString(), "F:\\Eclipse_Workspace\\ZipFilesBatchProcessing\\inputcsvs");
	    		}
	    		else if(file1.getName().endsWith("xlsx")) {
	    			FileUtils.copyFileToDirectory(file1, new File("F:\\Eclipse_Workspace\\ZipFilesBatchProcessing\\inputcsvs"));
	    		}
	    			
	    	}
	    	}
	        
	    }
	}
	
	
	private static void unzip(String zipFilePath, String destDir) {
		
		/*
		 * https://www.journaldev.com/960/java-unzip-file-example
		 */
        File dir = new File(destDir);
        FileInputStream fis;
        byte[] buffer = new byte[1024];
        try {
            fis = new FileInputStream(zipFilePath);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry ze = zis.getNextEntry();
            while(ze != null){
                String fileName = ze.getName();
                File newFile = new File(destDir + File.separator + fileName);
//                System.out.println("Unzipping to "+newFile.getAbsolutePath());
                new File(newFile.getParent()).mkdirs();
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
                }
                fos.close();
                zis.closeEntry();
                ze = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
	

	
}
