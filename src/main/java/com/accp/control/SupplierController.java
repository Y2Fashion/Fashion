package com.accp.control;

import com.accp.biz.SupplierBiz;
import com.accp.entity.Supplier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
/**
 *供应商管理
 */
@Controller
public class SupplierController {
    @Resource
    private SupplierBiz supplierBiz;
    /**
     *供应商
     */
    @RequestMapping("/Supplier")
    @GetMapping("/")
    public String supplier(Model model, Integer pageNo){
        model.addAttribute("pager",supplierBiz.findAll(pageNo,2));
        return "backstage/Supplier";
    }

    /**
     * 添加供应商页面
     */
    @RequestMapping(value = "/Supplier_add",method = RequestMethod.GET)
    public String supplier_add(){
        return "backstage/Supplier_add";
    }
    /**
     * 添加供应商
     */
    @RequestMapping(value = "/Supplier_add",method = RequestMethod.POST)
    @GetMapping("/")
    public String supplier_add(Supplier supplier){
        supplierBiz.Add(supplier);
        return "redirect:/Supplier";
    }

    /**
     * 查看供应商
     */
    @RequestMapping("/Supplier_get")
    @GetMapping("/")
    public String supplier_get(Model model,Integer id){
        model.addAttribute("Coop",supplierBiz.findById(id));
        return "backstage/Supplier_get";
    }

    /**
     * 修改供应商页面
     */
    @RequestMapping(value = "/Supplier_upd",method = RequestMethod.GET)
    @GetMapping("/")
    public String supplier_upd(Model model,Integer id){
        model.addAttribute("Coop",supplierBiz.findById(id));
        return "backstage/Supplier_upd";
    }
    /**
     * 修改供应商
     */
    @RequestMapping(value = "/Supplier_upd",method = RequestMethod.POST)
    @GetMapping("/")
    public String supplier_upd(Supplier supplier){
        supplierBiz.Update(supplier);
        return "redirect:/Supplier";
    }
    /**
     * 单个删除
     * @param id
     * @return
     */
    @RequestMapping("/delSupplier")
    @GetMapping("/")
    public String del(Integer id){
        supplierBiz.Del(id);

        return null;
    }

    /**
     * 批量删除
     * @param arr
     * @return
     */
    @RequestMapping("/delsSupplier")
    @GetMapping("/")
    @ResponseBody
    public String dels(@RequestParam(value = "arr[]") Integer[] arr){
        List<Integer> a= Arrays.asList(arr);
        for(Integer i:a){
            if(i!=null){
                //System.out.println(i);
                supplierBiz.Del(i);
            }

        }
        return "redirect:/Supplier";
    }
}
