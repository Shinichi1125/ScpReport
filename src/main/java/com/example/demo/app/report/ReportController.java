package com.example.demo.app.report;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Report;
import com.example.demo.entity.User;
import com.example.demo.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	// destination folder to upload the files 
	private static String UPLOAD_FOLDER = "C://springimage//";
	//private static String UPLOAD_FOLDER = "C://Users//marra//Documents//workspace-spring-tool-suite-4-4.5.1.RELEASE\\ScpReport//src//main//resources//static//img//";
	
	private final ReportService reportService;
	
	@Autowired
	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReportController.class);
	
  /**
	* Displays a list of reports
	* @param reportForm
	* @param model
	* @return HTML file under resources/templates
	*/
	@GetMapping
	public String report(ReportForm reportForm, Model model) {
		
		LOGGER.info("The default page called");
		
		// This statement makes POST a new post
		reportForm.setNewReport(true);
		
		// Get a list of reports
		List<Report> list = reportService.findAll();
		
		model.addAttribute("list", list);
		model.addAttribute("title", "SCP Report");
		
		return "report/index_boot";
	}
	
	/**
	* Inserts a single report
	* @param reportForm
	* @param result
	*/
	@PostMapping("/insert")
	public String insert(
			@Valid @ModelAttribute ReportForm reportForm,
			BindingResult result,
			Model model) {
		
			LOGGER.info("The report title is " + reportForm.getTitle());
			LOGGER.info("The content type is " + reportForm.getFile().getContentType());
		
			Report report = makeReport(reportForm, 0);
			
			if(!result.hasErrors()) {
				reportService.insert(report);
				return "redirect:/report";
			} else {
				reportForm.setNewReport(true);
				model.addAttribute("reportForm", reportForm);
				List<Report> list = reportService.findAll();
				model.addAttribute("list", list);
				model.addAttribute("title", "SCP Report");
				return "report/index_boot";
			}
	}
	
 /**
	* Gets a single report's data and displays it in form
	* @param reportForm
	* @param id
	* @param model
	* @return 
	*/
	@GetMapping("/{id}")
	public String showUpdate(
			ReportForm reportForm,
			@PathVariable int id,
			Model model) {
		
		// Gets Report (wrapping with Optional) 
		Optional<Report> reportOpt = reportService.getReport(id);
		
		// Converts the data to an Optional type
		Optional<ReportForm> reportFormOpt = reportOpt.map(r -> makeReportForm(r));
		
		// If the ReportForm is not NULL, extract the content
		if(reportFormOpt.isPresent()) {
			reportForm = reportFormOpt.get();
		}
		
		model.addAttribute("reportForm", reportForm);
		List<Report> list = reportService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("reportId", id);
		model.addAttribute("title", "Update Form");
		
		return "report/index_boot";
	}
	
  /**
	* Gets a report ID and updates a report 
	* @param reportForm
	* @param result
	* @param id
	* @param model
	* @param redirectAttributes
	* @return 
	*/
	@PostMapping("/update")
	public String update(
			@Valid @ModelAttribute ReportForm reportForm,
			BindingResult result, 
			@RequestParam("reportId") int reportId, 
			Model model,
			RedirectAttributes redirectAttributes) {
		
		// sets the data of ReportForm into Report
		Report report = makeReport(reportForm, reportId);
		
		if(!result.hasErrors()) {
			// processing the update, using flash scope, redirect
			reportService.update(report);
			redirectAttributes.addFlashAttribute("complete", "The update is complete");
			return "redirect:/report";   //  + "/" + reportId     // add reportId in case you don't want to automatically redirect
		} else {
			model.addAttribute("reportForm", reportForm);
			model.addAttribute("title", "SCP Report");
			return "report/index_boot";
		}
	}
	
  /**
	* Gets a report ID and deletes a report 
	* @param id
	* @param model
	* @return 
	*/
	@PostMapping("/delete")
	public String delete(
			@RequestParam("reportId") String id, // pathvariable
			Model model) {
		
		// deletes a report and redirects
		reportService.deleteById(Integer.parseInt(id));
		return "redirect:/report";
	}
	
  /**
	* Takes in the data of ReportForm, sets it in Report and returns it
	* @param reportForm
	* @param reportId (0 in case it's a new report)
	* @return 
	*/
	private Report makeReport(ReportForm reportForm, int reportId) {
		Report report = new Report();
		if(reportId != 0) {
			report.setReportId(reportId);
		} else {
			report.setReportId(findBiggestReportId()+1);   
		}
		
		User user = new User();
		
		user.setUserId(1);
		user.setUserName("Shinichi");
		report.setUser(user);
		
		report.setTitle(reportForm.getTitle());
		report.setThreatLevel(reportForm.getThreatId());
		report.setReportDate(reportForm.getReportedDate());
		report.setDescription(reportForm.getDetail());	
		try {
			report.setImage(reportForm.getFile().getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		report.setImgPath(UPLOAD_FOLDER + reportForm.getFile().getFileName());
//		report.setFile(reportForm.getFile());
		
		return report; 
	}
	
	/**
	* Converts data of Report into ReportForm
	* @param report
	* @return 
	*/
	private ReportForm makeReportForm(Report report) {
		ReportForm reportForm = new ReportForm();

		reportForm.setTitle(report.getTitle());
		reportForm.setThreatId(report.getThreatLevel());
		reportForm.setReportedDate(report.getReportDate());
		reportForm.setDetail(report.getDescription());	
		reportForm.setNewReport(false);
		
		return reportForm; 
	}
	
	private int findBiggestReportId() {
		int result = 0; 
		
		List<Report> reportList = reportService.findAll();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		
		for(Report list: reportList) {
			idList.add(list.getReportId());
		}	
		for(int i = 0; i < idList.size(); i++) {
			result = idList.get(i);
		}	
		return result; 
	}
	
	@PostMapping("/uploadFile")
    public String fileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
    	if(file.isEmpty()) {
    		return "report/error";
    	}
    	
    	try {
    		// read and write the file to the selected location
    		byte[] bytes = file.getBytes();
    		Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
    		Files.write(path, bytes);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return "redirect:/report";
    }
	
	@GetMapping("/springimage/{fileName}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName) throws IOException {
        String stringPath = UPLOAD_FOLDER + fileName;
		Path imagePath = Paths.get(stringPath);
		
        return ResponseEntity.ok()
        	    .contentType(MediaType.IMAGE_JPEG)
        	    .body(new InputStreamResource(Files.newInputStream(imagePath)));
    }
	
	@GetMapping("/uploaded-images/{reportId}")
	ResponseEntity<byte[]> reportImage(@PathVariable int reportId) {
	  Optional<Report> report = reportService.getReport(reportId); 
	  byte[] image = report.get().getImage();
	  return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
	}
}
