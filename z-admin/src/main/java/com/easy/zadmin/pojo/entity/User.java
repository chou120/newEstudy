package com.easy.zadmin.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_user")
public class User implements Serializable {
    private static final long serialVersionUID = -40356785423868312L;

    /**
     * 主键
     */
    @TableId
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 密码
     */
    private String password;
    /**
     * 账号状态（0正常 1停用）
     */
    private String status;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phonenumber;
    /**
     * 用户性别（0男，1女，2未知）
     */
    private String sex;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 用户类型（0管理员，1普通用户）
     */
    private String userType;
    /**
     * 创建人的用户id
     */
    private Long createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private Long updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    private Integer delFlag;


    private String tutorEngName;
    private String tutorUniversity;
    private String tutorEducation;
    private String tutorEducationBg;
    private String tutorMajor;
    private String tutorAdept;
    private String tutorCharacteristicAnalysis;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getTutorEngName() {
        return tutorEngName;
    }

    public void setTutorEngName(String tutorEngName) {
        this.tutorEngName = tutorEngName;
    }

    public String getTutorUniversity() {
        return tutorUniversity;
    }

    public void setTutorUniversity(String tutorUniversity) {
        this.tutorUniversity = tutorUniversity;
    }

    public String getTutorEducation() {
        return tutorEducation;
    }

    public void setTutorEducation(String tutorEducation) {
        this.tutorEducation = tutorEducation;
    }

    public String getTutorEducationBg() {
        return tutorEducationBg;
    }

    public void setTutorEducationBg(String tutorEducationBg) {
        this.tutorEducationBg = tutorEducationBg;
    }

    public String getTutorMajor() {
        return tutorMajor;
    }

    public void setTutorMajor(String tutorMajor) {
        this.tutorMajor = tutorMajor;
    }

    public String getTutorAdept() {
        return tutorAdept;
    }

    public void setTutorAdept(String tutorAdept) {
        this.tutorAdept = tutorAdept;
    }

    public String getTutorCharacteristicAnalysis() {
        return tutorCharacteristicAnalysis;
    }

    public void setTutorCharacteristicAnalysis(String tutorCharacteristicAnalysis) {
        this.tutorCharacteristicAnalysis = tutorCharacteristicAnalysis;
    }


//      <resultMap id="userMap" type="com.easy.zadmin.pojo.entity.User">
//        <id property="id" column="_id" />
//        <result property="user_name" column="userName" />
//        <result property="nick_name" column="nickName" />
//        <result property="password" column="password" />
//        <result property="status" column="status" />
//        <result property="email" column="email" />
//        <result property="phonenumber" column="phonenumber"/>
//        <result property="sex" column="sex" />
//        <result property="avatar" column="avatar" />
//        <result property="user_type" column="userType" />
//        <result property="create_by" column="createBy" />
//        <result property="create_time" column="createTime" />
//        <result property="update_by" column="updateBy" />
//        <result property="del_flag" column="delFlag" />
//        <result property="tutor_eng_name" column="tutorEngName" />
//        <result property="tutor_university" column="tutorUniversity" />
//        <result property="tutor_education" column="tutorEducation" />
//        <result property="tutor_education_bg" column="tutorEducationBg" />
//        <result property="tutor_major" column="tutorMajor" />
//        <result property="tutor_adept" column="tutorAdept" />
//        <result property="tutor_characteristic_analysis" column="tutorCharacteristicAnalysis"/>
//    </resultMap>
//
//</mapper>





}
