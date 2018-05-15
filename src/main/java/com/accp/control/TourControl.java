package com.accp.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TourControl {
   /* @Resource
    private TourBiz tourBiz = null;


    @RequestMapping("/ShowTours")
    public String showTours(Model m) {

        List<Tour> tours = tourBiz.queryTours();
        m.addAttribute("tours", tours);
        return "list";
    }

    @RequestMapping(value = "/DeleteTour", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String deleteTour(int tourId, HttpServletResponse resp) {
        if (tourBiz.deleteTour(tourId)) {
            return "<script>alert('删除成功');location='ShowTours' </script>";
        } else {
            return "<script>alert('删除失败');location='ShowTours' </script>";
        }
    }

    @RequestMapping(value = "/AddTour", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String addTour(@RequestParam(required = false) Tour t, HttpServletResponse resp) {
        if (tourBiz.saveTour(t)) {
            return "<script>alert('增加成功');location='ShowTours' </script>";
        } else {
            return "<script>alert('增加失败');location='ShowTours' </script>";
        }

    }*/
}
