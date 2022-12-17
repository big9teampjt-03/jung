package petcare.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import petcare.model.BoardPetstory;
import petcare.service.PetStoryBoardService;

@Controller
@RequestMapping("/board/*")
public class PetStoryBoardController {
	@Autowired
	private PetStoryBoardService petStoryBoardService;
	
	@GetMapping("insert")
	public String insert() {
		return "/board/boardPetStoryInsert";
	}
	
	@PostMapping("fileInsert")
	public String insert(BoardPetstory bpsboard, HttpSession session) {
		String uploadFolder = session.getServletContext().getRealPath("/")+"\\resources\\img";
		petStoryBoardService.fileInsert(bpsboard, uploadFolder);
		 
		 return "redirect:list";
	}
	
	@GetMapping("list")
	public String list(Model model) {
		model.addAttribute("lists",petStoryBoardService.list());
		return "/board/boardPetStoryList";
	}
	
	@GetMapping("view/{petstoryID}")
	public String view(@PathVariable Long petstoryID, Model model) {
		model.addAttribute("bpsboard",petStoryBoardService.findById(petstoryID));
		return "/board/boardPetStoryView";
	}
	
	//수정폼
	@GetMapping("update/{petstoryID}")
	public String update(@PathVariable Long petstoryID, Model model) {
		model.addAttribute("bpsboard", petStoryBoardService.findById(petstoryID));
		return "/board/boardPetStoryUpdate";
	}
	
	@PutMapping("updateLikes")
	@ResponseBody
	public long updateLikes(@RequestBody BoardPetstory boardPetStory) {
		//System.out.println("likes :"+boardPetStory.getLikes());
		
		return petStoryBoardService.updateLikes(boardPetStory);
	}
	
	
	@PutMapping("update")
	@ResponseBody
	public String update(@RequestBody BoardPetstory boardPetStory) {
		petStoryBoardService.update(boardPetStory);
		//petStoryBoardService.updateLikes(boardPetStory);
		return "success";
	}
	
	@DeleteMapping("delete/{petstoryID}")
	@ResponseBody
	public String delete(@PathVariable Long petstoryID) {
		petStoryBoardService.delete(petstoryID);
		return "success";
	} 
	

}
