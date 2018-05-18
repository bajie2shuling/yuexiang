package com.wjz.yuexiang.service;

import com.wjz.yuexiang.dao.BookForestCreateApplyRepository;
import com.wjz.yuexiang.dao.BookForestRepository;
import com.wjz.yuexiang.dao.BookReviewRepository;
import com.wjz.yuexiang.dao.VerifyRecordRepository;
import com.wjz.yuexiang.exception.NotFoundException;
import com.wjz.yuexiang.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by Jinzi Wu at 22:43 on 2018/5/6.
 */
@Service
public class VerifyRecordServiceImpl implements VerifyRecordService {

    @Autowired
    private VerifyRecordRepository verifyRecordRepository;

    @Autowired
    private BookReviewRepository bookReviewRepository;

    @Autowired
    private BookForestCreateApplyRepository bookForestCreateApplyRepository;

    @Autowired
    private BookForestRepository bookForestRepository;

    @Transactional
    @Override
    public VerifyRecord generateBookReviewVerifyRecord(Integer verifyResult, Integer bookReviewStatus, Long bookReviewId, Admin admin) {

        BookReview bookReview = bookReviewRepository.findByIdAndStatus(bookReviewId,1);  //只查待审核的书籍
        if(bookReview == null){
            throw new NotFoundException("该篇书评不存在");
        }else{
            bookReview.setStatus(bookReviewStatus);
            VerifyRecord verifyRecord = new VerifyRecord();
            verifyRecord.setUserId(bookReview.getUser().getId());
            verifyRecord.setNickName(bookReview.getUser().getNickName());
            verifyRecord.setAdmin(admin);
            verifyRecord.setBookReview(bookReview);
            verifyRecord.setCreateTime(new Date());
            verifyRecord.setVerifyContentBriefInfo(bookReview.getTitle() + "——" + bookReview.getBookName() + "——" + bookReview.getBookAuthor());
            verifyRecord.setResult(verifyResult);
            return verifyRecordRepository.save(verifyRecord);
        }
    }

    @Override
    public Page<VerifyRecord> verifyRecords(Admin admin,Pageable pageable) {
        Page<VerifyRecord> page = verifyRecordRepository.findAllByAdmin(admin,pageable);
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
    public VerifyRecord generateBookForestCreateApplyVerifyRecord(Integer verifyResult, Integer bookForestCreateApplyStatus, Long bookForestCreateApplyId, Admin admin) {
        BookForestCreateApply bookForestCreateApply = bookForestCreateApplyRepository.findByIdAndStatus(bookForestCreateApplyId,0);  //只查待审核的书林创建申请
        if(bookForestCreateApply == null){
            throw new NotFoundException("该条书林创建申请不存在");
        }else{
            bookForestCreateApply.setStatus(bookForestCreateApplyStatus);

            if(verifyResult == 1){
                //审核通过则创建书林
                BookForest bookForest = new BookForest();
                bookForest.setName(bookForestCreateApply.getBookForestName());
                bookForest.setDescription(bookForestCreateApply.getBookForestDescription());
                bookForest.setAdmin(admin);
                bookForest.setCreateTime(new Date());
                bookForestRepository.save(bookForest);
            }

            //生成审核记录
            VerifyRecord verifyRecord = new VerifyRecord();
            verifyRecord.setUserId(bookForestCreateApply.getUser().getId());
            verifyRecord.setNickName(bookForestCreateApply.getUser().getNickName());
            verifyRecord.setAdmin(admin);
            verifyRecord.setBookForestCreateApply(bookForestCreateApply);
            verifyRecord.setCreateTime(new Date());
            verifyRecord.setVerifyContentBriefInfo(bookForestCreateApply.getBookForestName() + "——" + bookForestCreateApply.getBookForestDescription());
            verifyRecord.setResult(verifyResult);
            return verifyRecordRepository.save(verifyRecord);
        }
    }
}
