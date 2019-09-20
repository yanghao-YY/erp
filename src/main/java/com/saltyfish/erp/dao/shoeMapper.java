package com.saltyfish.erp.dao;

import com.saltyfish.erp.domain.Shoes;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface shoeMapper {
    //库存加一
    @Update("update  shoes set stock=stock+1 where plu = #{plu}")
    void addStock(@Param("plu")String plu);
    //库存减一
    @Update("update  shoes set stock=stock-1 where plu = #{plu}")
    void cutStock(@Param("plu")String plu);
    //查询库存
    @SelectProvider(type = ServerSqlProvider.class)
    List<Shoes> query(@Param("model")String model, @Param("plu")String plu, @Param("color")String color,@Param("size")String size,@Param("remark")String remark);

    class ServerSqlProvider implements ProviderMethodResolver {
        public static String query(final @Param("model")String model,final  @Param("plu")String plu, final @Param("color")String color,final @Param("size")String size,final @Param("remark")String remark){
            SQL sql = new SQL().SELECT("*").FROM("shoes");

            if(!model.equals("")){
                sql.WHERE("model like  concat('%',#{model},'%')");
            }
            if(!plu.equals("")) {
                sql.WHERE("plu like concat('%',#{plu},'%')");
            }
            if(!color.equals("")){

                sql.WHERE("color like concat('%',#{color},'%')");
            }
            if(!size.equals("")){
                sql.WHERE("size like concat('%',#{size},'%')");
            }
            if(!remark.equals("")){
                sql.WHERE("remark like concat('%',#{remark},'%')");
            }
            sql.ORDER_BY("id desc");
            return sql.toString();

        }
    }
    //新增鞋子
    @Insert("insert into shoes(model,plu,color,size,stock,remark) values(#{model},#{plu},#{color},#{size},#{stock},#{remark})")
    void addShoes(@Param("model")String model, @Param("plu")String plu, @Param("color")String color, @Param("size")String size,@Param("stock")String stock,@Param("remark")String remark);
    //修改鞋子
    @Update("update shoes set model=#{model},plu=#{plu},color=#{color},size=#{size},stock=#{stock},remark=#{remark} where id=#{id}")
    void updateShoes(@Param("id")int id,@Param("model")String model, @Param("plu")String plu, @Param("color")String color, @Param("size")String size,@Param("stock")String stock,@Param("remark")String remark);
    //删除鞋子
    @Delete("delete from shoes where id = #{id}")
    void deleteShoes(@Param("id")int id);
}

