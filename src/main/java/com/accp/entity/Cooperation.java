package com.accp.entity;

import java.io.Serializable;
import java.util.Date;

public class Cooperation implements Serializable {
    private static final long serialVersionUID=1L;
    private Integer ID;
    private String QiYeName;//  企业名称
    private String NAME;//  姓名
    private Date CreateDate;//  成立时间
    private String Phone;//  联系方式
    private String QiYeAddress;//  公司地址
    private String zhiWu;//  公司职务
    private String FaRen;//  公司法人
    private String Email;//  邮箱
    private String YeWuFanWei;//  业务范围
    private String CanPingLei;//  产品类型
    private String CanNeng;//  产能
    private String JiaGongXS;//  加工形式
    private String CanPingFengGe;//  产品风格
    private String PingPaiHeZuo;//  品牌合作
    private Integer ZhongRenShu;//  公司总人数
    private Integer CheJIanRenShu;//  车间人数
    private Integer BanFangRen;//  板房人数
    private Integer ZaiChuangRenShu;//  载床人数
    private Integer DaTangRenShu;//  大烫人数
    private Integer HouDaoRenShu;//  后道人数
    private Integer ChangMianJi;//  工厂面积
    private Integer ChenShu;//  层数
    private Integer ZaiChuangMianJi;//  载床面积
    private Integer CheJianMianJi;//  车间面积
    private Integer HouZhenMianJi;// 后整面积
    private Integer ShuSheMianJi;//  宿舍面积
    private String YaoQiu;//  要求

    public Cooperation() {
    }


    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getQiYeName() {
        return QiYeName;
    }

    public void setQiYeName(String qiYeName) {
        QiYeName = qiYeName;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public Date getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Date createDate) {
        CreateDate = createDate;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getQiYeAddress() {
        return QiYeAddress;
    }

    public void setQiYeAddress(String qiYeAddress) {
        QiYeAddress = qiYeAddress;
    }

    public String getZhiWu() {
        return zhiWu;
    }

    public void setZhiWu(String zhiWu) {
        this.zhiWu = zhiWu;
    }

    public String getFaRen() {
        return FaRen;
    }

    public void setFaRen(String faRen) {
        FaRen = faRen;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getYeWuFanWei() {
        return YeWuFanWei;
    }

    public void setYeWuFanWei(String yeWuFanWei) {
        YeWuFanWei = yeWuFanWei;
    }

    public String getCanPingLei() {
        return CanPingLei;
    }

    public void setCanPingLei(String canPingLei) {
        CanPingLei = canPingLei;
    }

    public String getCanNeng() {
        return CanNeng;
    }

    public void setCanNeng(String canNeng) {
        CanNeng = canNeng;
    }

    public String getJiaGongXS() {
        return JiaGongXS;
    }

    public void setJiaGongXS(String jiaGongXS) {
        JiaGongXS = jiaGongXS;
    }

    public String getCanPingFengGe() {
        return CanPingFengGe;
    }

    public void setCanPingFengGe(String canPingFengGe) {
        CanPingFengGe = canPingFengGe;
    }

    public String getPingPaiHeZuo() {
        return PingPaiHeZuo;
    }

    public void setPingPaiHeZuo(String pingPaiHeZuo) {
        PingPaiHeZuo = pingPaiHeZuo;
    }

    public Integer getZhongRenShu() {
        return ZhongRenShu;
    }

    public void setZhongRenShu(Integer zhongRenShu) {
        ZhongRenShu = zhongRenShu;
    }

    public Integer getCheJIanRenShu() {
        return CheJIanRenShu;
    }

    public void setCheJIanRenShu(Integer cheJIanRenShu) {
        CheJIanRenShu = cheJIanRenShu;
    }

    public Integer getBanFangRen() {
        return BanFangRen;
    }

    public void setBanFangRen(Integer banFangRen) {
        BanFangRen = banFangRen;
    }

    public Integer getZaiChuangRenShu() {
        return ZaiChuangRenShu;
    }

    public void setZaiChuangRenShu(Integer zaiChuangRenShu) {
        ZaiChuangRenShu = zaiChuangRenShu;
    }

    public Integer getDaTangRenShu() {
        return DaTangRenShu;
    }

    public void setDaTangRenShu(Integer daTangRenShu) {
        DaTangRenShu = daTangRenShu;
    }

    public Integer getHouDaoRenShu() {
        return HouDaoRenShu;
    }

    public void setHouDaoRenShu(Integer houDaoRenShu) {
        HouDaoRenShu = houDaoRenShu;
    }

    public Integer getChangMianJi() {
        return ChangMianJi;
    }

    public void setChangMianJi(Integer changMianJi) {
        ChangMianJi = changMianJi;
    }

    public Integer getChenShu() {
        return ChenShu;
    }

    public void setChenShu(Integer chenShu) {
        ChenShu = chenShu;
    }

    public Integer getZaiChuangMianJi() {
        return ZaiChuangMianJi;
    }

    public void setZaiChuangMianJi(Integer zaiChuangMianJi) {
        ZaiChuangMianJi = zaiChuangMianJi;
    }

    public Integer getCheJianMianJi() {
        return CheJianMianJi;
    }

    public void setCheJianMianJi(Integer cheJianMianJi) {
        CheJianMianJi = cheJianMianJi;
    }

    public Integer getHouZhenMianJi() {
        return HouZhenMianJi;
    }

    public void setHouZhenMianJi(Integer houZhenMianJi) {
        HouZhenMianJi = houZhenMianJi;
    }

    public Integer getShuSheMianJi() {
        return ShuSheMianJi;
    }

    public void setShuSheMianJi(Integer shuSheMianJi) {
        ShuSheMianJi = shuSheMianJi;
    }

    public String getYaoQiu() {
        return YaoQiu;
    }

    public void setYaoQiu(String yaoQiu) {
        YaoQiu = yaoQiu;
    }
}
