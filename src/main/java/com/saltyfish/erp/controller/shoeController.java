package com.saltyfish.erp.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.saltyfish.erp.domain.Shoes;
import com.saltyfish.erp.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.saltyfish.erp.service.shoeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.saltyfish.erp.util.shoeData;

import java.util.List;

@RestController
public class shoeController {
    @Autowired
    shoeService shoeService;
    @PostMapping("/addStock")
    public Result addStock(@RequestParam("plus")String plus){
        try {
            String[] plu = plus.split("\n");
           for (int i=0;i < plu.length;i++){
               shoeService.addStock(plu[i]);
           }

        } catch (Exception e) {
            Result result = new Result("9999","系统异常，扫码入库失败",null);
             return result;
        }
        Result result = new Result("0000","扫码入库成功",null);
        return result;
    }
    @PostMapping("/cutStock")
    public Result cutStock(@RequestParam("plus")String plus){
        try {
            String[] plu = plus.split("\n");
            for (int i=0;i < plu.length;i++){
                shoeService.cutStock(plu[i]);
            }
        } catch (Exception e) {
            Result result = new Result("9999","系统异常，扫码出库失败",null);
            return result;
        }
        Result result = new Result("0000","扫码出库成功",null);
            return result;
    }
    //查询
    @PostMapping("/query")
    public shoeData query(@RequestParam("model")String model,@RequestParam("plu")String plu,@RequestParam("color")String color,@RequestParam("size")String size,@RequestParam("remark")String remark,@RequestParam(value = "pageSize",defaultValue = "1")int pageSize,@RequestParam(value = "pageNumber",defaultValue = "1")int pageNumber){
        try {
            //判断如何前端没有传分页信息，则判定为需要调用导出功能，不调用分页，返回查询的全部结果
            if ((pageNumber == 1) && (pageSize ==1)) {
                List<Shoes> shoesList = shoeService.query(model,plu,color,size,remark);
                PageInfo<Shoes> pageInfo = new PageInfo<>(shoesList);
                shoeData shoedata = new shoeData(pageInfo.getTotal(),shoesList);
                return shoedata;

            }
            else {
              //  System.out.println(pageNumber);
                PageHelper.startPage(pageNumber,pageSize);
                List<Shoes> shoesList = shoeService.query(model,plu,color,size,remark);
                PageInfo<Shoes> pageInfo = new PageInfo<>(shoesList);
                shoeData shoedata = new shoeData(pageInfo.getTotal(),shoesList);
//            Result result = new Result("0000","success",shoedata);
                return shoedata;
            }
        } catch (Exception e) {
//            Result result = new Result("9999","系统异常",null);
            return null;
        }

    }
    @PostMapping("/add")
    public  Result add(@RequestParam("model")String model,@RequestParam("plu")String plu,@RequestParam("color")String color,@RequestParam("size")String size,@RequestParam("stock")String stock,@RequestParam("remark")String remark){
        try {
            shoeService.add(model,plu,color,size,stock,remark);
        } catch (Exception e) {
            Result result = new Result("9999","新建鞋子信息失败",null);
            return result;
        }
        Result result = new Result("0000","新建鞋子信息成功",null);
        return result;
    }
    @PostMapping("/update")
    public Result update(@RequestParam("id")int id,@RequestParam("model")String model,@RequestParam("plu")String plu,@RequestParam("color")String color,@RequestParam("size")String size,@RequestParam("stock")String stock,@RequestParam("remark")String remark){
        try {
            shoeService.update(id, model, plu, color, size, stock, remark);
            return new Result("0000","更新鞋子库存成功",null);
        } catch (Exception e) {
            return new Result("9999","更新鞋子库存失败",null);
        }
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam("id")int id){
        try {
            shoeService.delete(id);
            return new Result("0000","删除鞋子信息成功",null);
        } catch (Exception e) {
            return new Result("9999","删除鞋子信息失败",null);
        }
    }
    @GetMapping("/queryByplu")
  public Shoes queryByplu(@RequestParam("plu")String plu){
        try {
            Shoes shoes = shoeService.queryByplu(plu);
            return shoes;
        } catch (Exception e) {

            return null;
        }

    }
}
