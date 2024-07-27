<template>
  <div>
    <div>
    <el-carousel :interval="1000" type="card" height="300px">
      <el-carousel-item v-for="item in imgList" :key="item.id">
        <img style="object-fit: scale-down; width: 100%;height: 100%" :src="item.idView" class="image">
      </el-carousel-item>
    </el-carousel>
    </div>
    <div>
      <el-row :gutter="100" >
        <el-col :span="6" :offset="6"><div class="grid-content bg-purple" >
          <el-container>
            <el-header  style=" height: 30px; line-height: 25px; text-align: left; border-radius: 4px">用户管理系统</el-header>
            <el-main style=" line-height: 50px; border-radius: 4px">
              支持管理员和普通用户的注册、登录、信息
            </el-main>
          </el-container>
        </div></el-col>

        <el-col :span="6"><div class="grid-content bg-purple">
          <el-container>
            <el-header  style=" height: 30px; line-height: 25px; text-align: left; border-radius: 4px">用户管理系统</el-header>
            <el-main style=" line-height: 50px; border-radius: 4px">
              支持管理员和普通用户的注册、登录、信息
            </el-main>
          </el-container>
        </div></el-col>
      </el-row>
      <el-row :gutter="100">
        <el-col :span="6" :offset="6"><div class="grid-content bg-purple">
          <el-container>
            <el-header  style=" height: 30px; line-height: 25px; text-align: left; border-radius: 4px">用户管理系统</el-header>
            <el-main style=" line-height: 50px; border-radius: 4px">
              支持管理员和普通用户的注册、登录、信息
            </el-main>
          </el-container>
        </div></el-col>
        <el-col :span="6"><div class="grid-content bg-purple">
          <el-container>
            <el-header  style=" height: 30px; line-height: 25px; text-align: left; border-radius: 4px">用户管理系统</el-header>
            <el-main style=" line-height: 50px; border-radius: 4px">
              支持管理员和普通用户的注册、登录、信息
            </el-main>
          </el-container>
        </div></el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
  export default {
    data(){
      return {
        imgList: [
          {id: 0, idView:require("../assets/home1.jpg")},
          {id: 1, idView:require("../assets/home2.jpg")},
          {id: 2, idView:require("../assets/home3.jpg")},

        ],
        screenWidth :0
      }
    }
  }
</script>


<style>
  .el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
  }
  .el-row {
    margin-bottom: 20px;
  &:last-child {
     margin-bottom: 0;
   }
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
</style>
