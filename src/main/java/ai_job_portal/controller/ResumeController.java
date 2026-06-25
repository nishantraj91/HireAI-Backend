package ai_job_portal.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import ai_job_portal.entity.Resume;
import ai_job_portal.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeRepository resumeRepository;

    private static final String UPLOAD_DIR =
            System.getProperty("user.dir")
                    + File.separator
                    + "uploads"
                    + File.separator;

    @GetMapping("/test")
    public String test() {
        return "Resume API Working";
    }

    @PostMapping("/upload")
    public String uploadResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") Long userId)
            throws IOException {

        File uploadDir = new File(UPLOAD_DIR);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName =
                System.currentTimeMillis()
                        + "_"
                        + file.getOriginalFilename();

        File destination =
                new File(UPLOAD_DIR, fileName);

        file.transferTo(destination);

        Resume resume = new Resume();

        resume.setUserId(userId);
        resume.setFileName(fileName);
        resume.setFilePath(destination.getAbsolutePath());

        resumeRepository.save(resume);

        return "Resume uploaded and saved in DB: "
                + fileName;
    }

    @GetMapping("/resume-by-user/{userId}")
    public Resume getResumeByUserId(
            @PathVariable Long userId) {

        return resumeRepository
                .findTopByUserIdOrderByIdDesc(userId);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello Resume";
    }
    @GetMapping("/download/{userId}")
    public Resume downloadResume(
            @PathVariable Long userId) {

        return resumeRepository
                .findTopByUserIdOrderByIdDesc(userId);
    }
    @GetMapping("/view/{userId}")
    public ResponseEntity<Resource> viewResume(
            @PathVariable Long userId)
            throws MalformedURLException {

        Resume resume =
                resumeRepository
                        .findTopByUserIdOrderByIdDesc(userId);

        if (resume == null) {
            return ResponseEntity.notFound().build();
        }

        Path path =
                Paths.get(resume.getFilePath());

        Resource resource =
                new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"" +
                                resume.getFileName() + "\""
                )
                .body(resource);
    }
}