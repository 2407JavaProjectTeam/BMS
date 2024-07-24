<template>
    <div class="app-container">
      <el-row :gutter="20">
        <el-col :span="6" :xs="24">
          <el-card class="box-card">
            <div class="user-info text item">
              {{userInfo.userInfo}}
            </div>
            <el-divider class="divider-height"></el-divider>
            <div class="text item user-img">
<!--              <el-avatar shape="circle" :size="150" :src="url"></el-avatar>-->
              <div>
                <el-upload
                  class="avatar-uploader"
                  action=""
                  :show-file-list="false"
                  :http-request="uploadFile"
                >
                  <img v-if="url" :src="url"  class="avatar" style="width: 140px;height: 140px" >
                  <div v-if="!url" class="avatar-uploader-icon">
                    <i class="el-icon-upload"></i>
                  </div>
                  <div class="avatar-uploader-overlay">
                    <i class="el-icon-plus"></i>
                  </div>
                </el-upload>
              </div>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="el-icon-user-solid"></i>用户名称</span>
                </el-col>
                <el-col :span="8" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="8" :xs="24" class="col-right">
                  <span>{{userInfo.userName}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="iconfont icon-shouji"></i>手机号码</span>
                </el-col>
                <el-col :span="8" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="8" :xs="24" class="col-right">
                  <span>{{userInfo.userTel}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="iconfont icon-youxiang"></i>用户邮箱</span>
                </el-col>
                <el-col :span="7" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="9" :xs="24" class="col-right">
                  <span>{{userInfo.userEmail}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="iconfont icon-bumenguanli"></i>所属部门</span>
                </el-col>
                <el-col :span="7" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="9" :xs="24" class="col-right">
                  <span>{{userInfo.userDept}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="iconfont icon-jiaose"></i>所属角色</span>
                </el-col>
                <el-col :span="7" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="9" :xs="24" class="col-right">
                  <span>{{userInfo.userRole}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
            <div class="user-info text item">
              <el-row :gutter="20">
                <el-col :span="8" :xs="24">
                  <span><i class="iconfont icon-riqi"></i>创建日期</span>
                </el-col>
                <el-col :span="7" :xs="24">
                  <span>&nbsp;</span>
                </el-col>
                <el-col :span="9" :xs="24" class="col-right">
                  <span>{{userInfo.userCreatedate}}</span>
                </el-col>
              </el-row>
            </div>
            <el-divider></el-divider>
          </el-card>
        </el-col>
        <el-col :span="16" :xs="24" :offset="2">
          <el-card class="box-card1">
              <template>
                <el-tabs v-model="activeName" @tab-click="" style="line-height: 10px;padding: 1px">
                  <el-tab-pane label="基本资料" name="first">
                    <el-form :label-position="labelPosition" ref="ruleForm" label-width="100px" class="demo-ruleForm" >
                      <el-form-item label="用户姓名" class="label-font" required>
                        <el-input></el-input>
                      </el-form-item>
                      <el-form-item label="手机号码" class="label-font" required>
                        <el-input></el-input>
                      </el-form-item>
                      <el-form-item label="出生日期" class="label-font" required>
                        <el-date-picker type="date" placeholder="选择日期" style="width: 100%;"></el-date-picker>
                      </el-form-item>
                      <el-form-item label="性别" class="label-font">
                        <el-radio-group>
                          <el-radio label="男"></el-radio>
                          <el-radio label="女"></el-radio>
                        </el-radio-group>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="">保存</el-button>
                        <el-button type="danger" @click="">关闭</el-button>
                      </el-form-item>
                    </el-form>
                  </el-tab-pane>
                  <el-tab-pane label="修改密码"  name="second">
                    <el-form :model="userPasswordInfo" :label-position="labelPosition" status-icon :rules="rules"  ref="ruleFormPwd" label-width="100px" class="demo-ruleForm">
                      <el-form-item label="旧密码" class="label-font" required prop="oldPassword">
                        <el-input type="password" v-model="userPasswordInfo.oldPassword" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="新密码" class="label-font" required prop="newPassword">
                        <el-input type="password" v-model="userPasswordInfo.newPassword" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item label="确认密码" class="label-font" required prop="confirmPassword">
                        <el-input type="password" v-model="userPasswordInfo.confirmPassword" autocomplete="off"></el-input>
                      </el-form-item>
                      <el-form-item>
                        <el-button type="primary" @click="updateUserPassword('ruleFormPwd')">保存</el-button>
                        <el-button type="danger" @click="">关闭</el-button>
                      </el-form-item>
                    </el-form>
                  </el-tab-pane>
                </el-tabs>
              </template>
          </el-card>
        </el-col>
      </el-row>
    </div>

</template>

<script>
    export default {
        name: "UserProfile",
        data(){
          const _self = this
          var checkOldPassword = (rule, value, callback) => {
             _self.$http.post("/api/checkPwd",{oldPassword:_self.userPasswordInfo.oldPassword}).then(resp=>{
               if(resp.data.code==200){
                 callback()
               }else {
                 callback(new Error('密码错误！'));
               }
             })
          };
          var checkNewPassword = (rule, value, callback) => {
              if(value==='' || typeof value==='undefined'){
                callback(new Error('请输入密码！'))
              }else{
                callback()
              }
          };
          var checkConfirmPassword = (rule, value, callback) => {
            if(value==='' || typeof value==='undefined'){
              callback(new Error('请输入密码！'))
            }else{
              if(_self.userPasswordInfo.newPassword == _self.userPasswordInfo.confirmPassword){
                callback()
              }else {
                callback(new Error('两次密码不一致！'))
              }
            }
          };

          return {
            userInfo:{
              userInfo:'个人信息',
              userName:undefined,
              userTel:undefined,
              userEmail:undefined,
              userDept:undefined,
              userRole:undefined,
              userCreatedate:undefined
            },
            url:undefined,
            activeName:'first',
            labelPosition:'right',
            userPasswordInfo:{
              oldPassword:undefined,
              newPassword:undefined,
              confirmPassword:undefined
            },
            rules: {
              oldPassword: [
                { validator: checkOldPassword, trigger: 'blur' }
              ],
              newPassword: [
                { validator: checkNewPassword, trigger: 'blur' }
              ],
              confirmPassword: [
                { validator: checkConfirmPassword, trigger: 'blur' }
              ]
            }
          };
        },
      methods:{
        uploadFile(param){
          const _self = this
          let file = param.file
          //创建表单数据对象
          let formData = new FormData();
          formData.append("file",file)
          _self.$http.post("/api/upload",formData).then(resp=>{
            if(resp.data.code==200){
              console.log(resp)
              _self.$message.success(resp.data.msg)
              _self.url = "http://localhost:8088"+resp.data.data
              _self.$emit("change_url",_self.url)
            }else {
              _self.$message.error(resp.data.msg)
            }
          })
        },
        updateUserPassword(updatePwdForm){
          const _self = this
          _self.$refs[updatePwdForm].validate((valid) => {
            if (valid) {
             //表单验证通过就发送请求
              _self.$http.put("/api/pwd",_self.userPasswordInfo).then(resp=>{
                if(resp.data.code==200){
                  //将表单清空
                  _self.$refs[updatePwdForm].resetFields();
                  //退出
                  _self.$http.get("/api/logout").then(function (resp) {
                    sessionStorage.clear()
                    _self.$router.replace("/login")
                    _self.$router.go(0)
                  })
                }else {
                  _self.$message.error(resp.data.msg)
                }
              })
            } else {
              return false;
            }
          });
        }
      },
      mounted() {
        this.userInfo.userName = sessionStorage.getItem("username")
        this.userInfo.userTel = sessionStorage.getItem("usertel")
        this.userInfo.userEmail = sessionStorage.getItem("useremail")
        this.userInfo.userDept = sessionStorage.getItem("userdept")
        this.userInfo.userRole = sessionStorage.getItem("userrole")
        this.userInfo.userCreatedate = sessionStorage.getItem("usercreatedate")
        this.url = sessionStorage.getItem("imgUrl")
        if(this.url.substring(0,7)==="http://") {
          this.url = this.url
        } else {
          this.url = "http://localhost:8088"+ this.url
        }
      }
    }
</script>

<style scoped>
  .user-info{
    text-align: left;
    line-height: 10px;
  }
  .user-img{
    text-align: center;
    line-height: 10px;
  }
  .col-right{
    text-align: right;
  }
  .text {
    font-size: 14px;
  }

  .item {
    padding: 18px 0;
  }

  .box-card {
    width: 400px;
  }
  .el-divider--horizontal{
    margin: 0px;
  }
  .box-card1{
    width: 800px;
  }
  .el-tabs__nav{
    padding: 1px;
  }
  .el-form-item{
    text-align: left;
  }
  .label-font{
    font-weight: bold;
  }
  .avatar-uploader {
    width: 140px;
    height: 140px;
    border: 1px solid #d9d9d9;
    border-radius: 50%;
    overflow: hidden;
    position: relative;
    margin-left: 100px;
  }

  .avatar {
    width: 100%;
    height: 100%;
    display: block;
  }

  .avatar-uploader-icon {
    width: 100%;
    height: 100%;
    line-height: 140px;
    text-align: center;
    font-size: 36px;
    color: #8c939d;
  }

  .avatar-uploader-overlay {
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(0, 0, 0, 0.24);
    color: rgba(255, 255, 255, 0.9);
    font-size: 28px;
    text-align: center;
    line-height: 140px;
    border-radius: 50%;
    opacity: 0;
    transition: opacity 0.3s ease;
    cursor: pointer;
  }

  .avatar-uploader:hover .avatar-uploader-overlay {
    opacity: 1;
  }
</style>
