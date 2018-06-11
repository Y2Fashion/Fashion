package com.accp.entity;

import java.io.Serializable;

public class Supplier implements Serializable {

        private static final long serialVersionUID=1L;
        private Integer ID;
        private String QiYeName;//  企业名称
        private String Phone;//  联系方式
        private String QiYeAddress;//  公司地址
        private String FaRen;//  公司法人
        private String YaoQiu;//  备注
        private String qualityName;//质量联系人
        private String businessName;//业务联系人
        private String qualityphone;//质量联系人电话
        private String businessphone;//业务联系人电话
        private String level;//合作级别
        private String company;//公司性质
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

        public String getFaRen() {
            return FaRen;
        }

        public void setFaRen(String faRen) {
            FaRen = faRen;
        }

        public String getYaoQiu() {
            return YaoQiu;
        }

        public void setYaoQiu(String yaoQiu) {
            YaoQiu = yaoQiu;
        }

        public String getQualityName() {
            return qualityName;
        }

        public void setQualityName(String qualityName) {
            this.qualityName = qualityName;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public String getQualityphone() {
            return qualityphone;
        }

        public void setQualityphone(String qualityphone) {
            this.qualityphone = qualityphone;
        }

        public String getBusinessphone() {
            return businessphone;
        }

        public void setBusinessphone(String businessphone) {
            this.businessphone = businessphone;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public String getCompany() {
            return company;
        }

        public void setCompany(String company) {
            this.company = company;
        }


}
