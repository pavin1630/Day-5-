package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Books;
import com.example.demo.service.BooksService;

@RestController
public class BooksController {
	@Autowired
	BooksService bs;

	@PostMapping("/postBook")
	public String addBooks(@RequestBody List<Books> b)
	{
		try {
			bs.addBooks(b);
			return "Tuples inserted successfully!";
		}
		catch(Exception e)
		{
			return e.getMessage();
		}
	}

	@GetMapping("/getBooks")
	public List<Books> showBooks()
	{
		return (List<Books>)bs.showAll();
	}

	@GetMapping("/getWithId")
	public Optional<Books> showWithId(@RequestParam(value = "id")int id)
	{
		return bs.showOne(id);
	}

	@GetMapping("sort/{str}")
	public List<Books> sortedInfo(@PathVariable String str)
	{
		return bs.sortedOrder(str);
	}

	@PutMapping("/putBooks")
	public String updatedInfo(@RequestParam(value = "id") int id, @RequestBody Books b)
	{
		return bs.updateOne(id, b);
	}

	@DeleteMapping("/deleteBooks")
	public String deleteInfo(@RequestParam(value = "id") int id)
	{
		if(bs.deleteOne(id))
		{
			return id + " is deleted successfully!";
		}
		else {
			return "Unable to delete " + id;
		}
	}
}