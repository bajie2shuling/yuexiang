package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.BookForestRepository;
import com.wjz.yuexiang.dao.BookRepository;
import com.wjz.yuexiang.dao.BookReviewRepository;
import com.wjz.yuexiang.exception.NotFoundException;
import com.wjz.yuexiang.po.Book;
import com.wjz.yuexiang.po.BookForest;
import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * Created by Jinzi Wu at 16:46 on 2018/5/31.
 */
@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookForestRepository bookForestRepository;

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @Override
    public Page<Book> books(Long bookForestId, Pageable pageable) {
        Page<Book> page = bookRepository.findAllByBookForestId(bookForestId,pageable);
        if(page.getTotalPages() == 0){
            return page;
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            throw new NotFoundException("很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //防止用户恶意在地址栏输入页码
        }
        return page;
    }

    @Transactional
    @Override
    public Book saveBook(Book book, User user,BookReview bookReview, Long bookForestId) {
        Optional<BookForest> optionalBookForest = bookForestRepository.findById(bookForestId);
        BookForest bookForest = optionalBookForest.orElseThrow(()-> new NotFoundException("对不起，该书林不存在！"));

        book.setAuthor(bookReview.getBookAuthor());
        book.setName(bookReview.getBookName());
        book.setFirstPicture(bookReview.getFirstPicture());
        book.setCreateTime(new Date());
        book.setUser(user);
        book.setBookForest(bookForest);
        book.setStatus(false);
        book.setVerifyStatus(0);

        return bookRepository.save(book);
    }

    @Override
    public Page<Book> books(User user, Pageable pageable) {
        Page<Book> page = bookRepository.findAllByUser(user,pageable);
        if(page.getTotalPages() == 0){
            return page;
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            throw new NotFoundException("很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //防止用户恶意在地址栏输入页码
        }
        return page;
    }
}
