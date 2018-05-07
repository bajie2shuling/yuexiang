package com.wjz.yuexiang.dao;

import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.po.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by Jinzi Wu at 17:42 on 2018/5/3.
 */
public interface BookReviewRepository extends JpaRepository<BookReview,Long>{

    @Transactional
    @Modifying
    @Query("update BookReview b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select b from BookReview b where b.id = ?1 and b.user.id = ?2")
    BookReview findByIdAndUserId(Long id,Long userId);

    BookReview findByIdAndUser(Long id,User user);  //与上面方法作用相同，参数不同而已

    @Transactional
    void deleteByIdAndUser(Long id,User user);

    //以上三个JPQ语句定义的方法也可以用user对象为参数去查，此处仅仅为了练习，不要局限

    BookReview findByIdAndStatus(Long id,Integer status);

    Page<BookReview> findAllByStatus(Integer status, Pageable pageable);

    Page<BookReview> findAllByUser(User user, Pageable pageable);



}
