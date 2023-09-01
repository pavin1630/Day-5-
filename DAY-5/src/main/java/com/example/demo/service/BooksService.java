package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Books;
import com.example.demo.repository.BooksRepo;

@Service
public class BooksService {

	@Autowired
	BooksRepo br;

	public List<Books> addBooks(List<Books> b)
	{
		return (List<Books>)br.saveAll(b);
	}

	public List<Books> showAll()
	{
		return br.findAll();
	}

	public Optional<Books> showOne(int id)
	{
		return br.findById(id);
	}

	public String updateOne(int id, Books b)
	{
		Books obj = br.getReferenceById(id);
		if(obj != null)
		{
			if(b.getId() != 0)
			{
				obj.setId(b.getId());
			}
			if(b.getBookName() != null)
			{
				obj.setBookName(b.getBookName());
			}
			if(b.getAuthorName() != null)
			{
				obj.setAuthorName(b.getAuthorName());
			}
			if(b.getPrice() != 0.0)
			{
				obj.setPrice(b.getPrice());
			}
			if(b.getQuantity() != 0)
			{
				obj.setQuantity(b.getQuantity());
			}
			br.save(obj);
			return id + " updated successfully!";
		}
		else {
			return id + " failed to update";

		}
	}
	public boolean deleteOne(int id)
	{
		Optional<Books> opt= br.findById(id);
		if(opt.isPresent())
		{
			try {
				br.deleteById(id);
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		else {
			return false;
		}
	}

	public List<Books> sortedOrder(String id)
	{
		return br.findAll(Sort.by(Sort.DEFAULT_DIRECTION,id));
	}
}