<template>
  <el-container style="height: 600px; border: 1px solid #eee">
    <el-container>
      <el-header  style=" height: 90px; line-height: 30px; text-align: left;">
        <el-form :inline="true"  class="demo-form-inline">
          <br/>
          <el-form-item label="书名">
            <el-input v-model="bookQueryParams.title"  placeholder="书名"></el-input>
          </el-form-item>
          <el-form-item label="作者">
            <el-input v-model="bookQueryParams.author"  placeholder="作者"></el-input>
          </el-form-item>
          <el-form-item label="分类">
            <el-input  v-model="bookQueryParams.categoryName" placeholder="分类"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="conditionQuery" >查询</el-button>
          </el-form-item>
          <el-form-item>已借阅数量：</el-form-item>
          <el-form-item></el-form-item>
        </el-form>
      </el-header>

      <el-main>
        <el-table  :data="tableData" border
                   style="width: 100%;line-height: 40px"
                   :default-sort = "{prop: 'title', order: 'descending'}"
        >

          <el-table-column prop="categoryName" label="类别" width="140">
          </el-table-column>
          <el-table-column prop="title" label="书名" width="120" sortable>
          </el-table-column>
          <el-table-column prop="author" label="作者">
          </el-table-column>
          <el-table-column prop="bookNum" label="剩余数量" sortable>
          </el-table-column>
          <el-table-column prop="bookBorrowNum" label="借出数量">
          </el-table-column>
          <el-table-column prop="address" label="操作">
            <template slot-scope="scope">
              <el-button type="primary" @click="borrowBook(scope.row)" >借阅</el-button>
            </template>
          </el-table-column>
        </el-table>
        <div class="block">
          <el-pagination
            :current-page="currentPage"
            :page-sizes="[2, 4, 5]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="totals" class="el-pagination"
            @current-change="currentChange"
            @size-change="sizeChange"
          >
          </el-pagination>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
</style>

<script>
  export default {
    data() {
      return {
        value:"",
        //表格展示数据
        tableData: [],
        tableData1: [],
        //分页参数
        currentPage:1,
        pageSize:2,
        totals:0,//总条数
        formLabelWidth: '120px',
        userQueryParams: {
          userName: undefined,
          userRname: undefined,
          roleName: undefined,
          userStatus: undefined,
          currentPage: 1,
          pageSize: 5
        },
        bookQueryParams:{
          title: undefined,
          author: undefined,
          categoryName: undefined,
          name: undefined,
          currentPage:1,
          pageSize:2,
        },
        borrowInfo:{
          userId: undefined,
          bookId: undefined,
          bookNum: undefined,
          bookBorrowNum: undefined,
        }

      }
    },
    methods:{
      queryUserInfo(){
        const _self = this
        _self.$http.get("/api/user",{
          params: _self.userQueryParams
        }).then(function (resp) {
          console.log(resp)
          if(resp.data.code==200){
            _self.tableData1 = resp.data.data.userInfos
          }
        })
      },
      queryBookInfo(){
        const _self = this
        _self.$http.get("/api/bookM1",{
          params: _self.bookQueryParams
        }).then(function (resp) {
            console.log(resp)
            if(resp.data.code==200){
              _self.tableData = resp.data.data.bookInfos
              _self.totals = resp.data.data.totals
            }
          }
        )
      },
      currentChange(cpage){
        this.bookQueryParams.currentPage = cpage
        this.currentPage = this.bookQueryParams.currentPage
        this.queryBookInfo()
      },
      sizeChange(psize){
        this.bookQueryParams.pageSize = psize
        this.pageSize = this.bookQueryParams.pageSize
        this.bookQueryParams.currentPage = 1
        this.queryBookInfo()
      },
      conditionQuery(){
        const _self = this
        _self.bookQueryParams.currentPage = 1
        _self.queryBookInfo()
      },
      borrowBook(row){
        const _self = this

        _self.$confirm('确认借阅本书吗？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(function () {
          if(row.bookNum<=0){
            _self.$message.error("该书已被借完！")
          }else {
          _self.borrowInfo.userId = sessionStorage.getItem("userid")
          _self.borrowInfo.bookId = row.bookId
          _self.borrowInfo.bookNum = row.bookNum
          _self.borrowInfo.bookBorrowNum = row.bookBorrowNum
          _self.$http.put("/api/borrow",_self.borrowInfo).then(resp=>{
            if(resp.data.code==200){
              _self.$message.success(resp.data.msg)
              _self.bookQueryParams.currentPage = 1
              _self.queryBookInfo()
            }
          })}
        })
      },
    },

    mounted() {
      this.queryBookInfo()
    }
  };

</script>
