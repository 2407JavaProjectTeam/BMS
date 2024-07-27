<template>
<el-container style="height: 500px; border: 1px solid #eee">
  <el-container>
    <el-header  style=" height: 90px; line-height: 30px; text-align: left;">
      <el-form :inline="true"  class="demo-form-inline">
        <br/>
        <el-form-item label="书名">
          <el-input   placeholder="书名"></el-input>
        </el-form-item>
        <el-form-item label="作者">
          <el-input   placeholder="作者"></el-input>
        </el-form-item>
        <el-form-item label="分类号">
          <el-input   placeholder="分类号"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" >查询</el-button>
        </el-form-item>
      </el-form>
    </el-header>

    <el-main>
      <el-table :data="tableData" border
                style="width: 100%;line-height: 40px">

        <el-table-column prop="date" label="图书编号" width="140"  sortable>
        </el-table-column>
        <el-table-column prop="name" label="书名" width="120">
        </el-table-column>
        <el-table-column prop="address" label="作者">
        </el-table-column>
        <el-table-column prop="address" label="借阅时间">
        </el-table-column>
        <el-table-column prop="address" label="归还时间">
        </el-table-column>
        <el-table-column prop="address" label="操作">
        </el-table-column>
      </el-table>
      <div class="block">
        <el-pagination
          :current-page="1"
          :page-sizes="[2, 4, 5]"
          :page-size="5"
          layout="total, sizes, prev, pager, next, jumper"
          :total="50" class="el-pagination"
        >
        </el-pagination>
      </div>
    </el-main>
  </el-container>
</el-container>
</template>
<style>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
</style>

<script>
  export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item)
      }
    },
    methods:{
      selectChange(selection){
        // console.log(selection)
        const _self = this
        _self.ids = []
        for(let i = 0;i<selection.length;i++){
          console.log(selection[i])
        }
      },
    }

  };
</script>
