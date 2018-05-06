package com.wjz.yuexiang.web.admin;

import com.wjz.yuexiang.po.BookReview;
import com.wjz.yuexiang.service.BookReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Jinzi Wu at 21:25 on 2018/5/5.
 */
@Controller
@RequestMapping("/admin")
public class BookReviewVerifyController {

    @Autowired
    private BookReviewService bookReviewService;

    @GetMapping("/book_review_verify_list")
    public String bookReviewVerifyPage(@PageableDefault(size = 8,sort = {"updateTime","createTime"},direction = Sort.Direction.ASC) Pageable pageable,
                                       RedirectAttributes attributes,
                                       Model model){
        Page<BookReview> page = bookReviewService.bookReviews(2,pageable);
        if(page.getTotalPages() == 0){
            page = new Page<BookReview>() {
                @Override
                public int getTotalPages() {
                    return 0;
                }

                @Override
                public long getTotalElements() {
                    return 0;
                }

                @Override
                public <U> Page<U> map(Function<? super BookReview, ? extends U> function) {
                    return null;
                }

                @Override
                public int getNumber() {
                    return 0;
                }

                @Override
                public int getSize() {
                    return 0;
                }

                @Override
                public int getNumberOfElements() {
                    return 0;
                }

                @Override
                public List<BookReview> getContent() {
                    return null;
                }

                @Override
                public boolean hasContent() {
                    return false;
                }

                @Override
                public Sort getSort() {
                    return null;
                }

                @Override
                public boolean isFirst() {
                    return false;
                }

                @Override
                public boolean isLast() {
                    return false;
                }

                @Override
                public boolean hasNext() {
                    return false;
                }

                @Override
                public boolean hasPrevious() {
                    return false;
                }

                @Override
                public Pageable nextPageable() {
                    return null;
                }

                @Override
                public Pageable previousPageable() {
                    return null;
                }

                @Override
                public Iterator<BookReview> iterator() {
                    return null;
                }
            };
            model.addAttribute("page",page);
            model.addAttribute("verifyMessage","还没有待审核的书评");
            return "admin/book_review_verify_list";
        }
        if(page.getPageable().getPageNumber() > page.getTotalPages()-1){
            attributes.addFlashAttribute("lostMessage","很遗憾，第" + page.getPageable().getPageNumber()+1 + "页不存在！");  //防止用户恶意在地址栏输入页码
            return "redirect:/admin/book_review_verify_list";
        }
        model.addAttribute("page",page);
        return "admin/book_review_verify_list";
    }
}
