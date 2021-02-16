<template>
    <el-table
            :data="tableData"
            border
            style="width:691px;">
        <el-table-column
                fixed
                prop="bookname"
                label="书名"
                width="150">
        </el-table-column>
        <el-table-column
                prop="context"
                label="概要"
                width="120">
        </el-table-column>
        <el-table-column
                prop="person"
                label="作者"
                width="120">
        </el-table-column>
        <el-table-column
                prop="time"
                label="时间"
                width="200">
        </el-table-column>
        <el-table-column
                label="操作"
                width="100">
            <template slot-scope="scope">
                <el-button @click="handleClick(scope.row)" type="text" size="small">修改</el-button>
                <el-button type="text" @click="deleteDate(scope.row)" size="small">删除</el-button>
            </template>
        </el-table-column>
    </el-table>
</template>

<script>
    export default {
        methods: {
            deleteDate(row){
                this.$confirm('此操作将永久删除该记录, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!',
                        options:myDele()
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
                function myDele() {
                    axios.defaults.withCredentials = false
                    axios.delete("http://localhost:8181/index/deleteData/"+row.bookname).then(resp=>{
                        if (resp.data =="ok"){
                            window.location.reload();
                        }
                    })
                }
            },
            handleClick(row) {
                this.$router.push({
                    path:"/upDate",
                    query:{
                        bookname:row.bookname
                    }
                })
            }
        },

        data() {
            return {
                tableData: [{
                    bookname:"",
                    context:"",
                    person:"",
                    time:""
                }
                ]
            }
        },
        created() {
            const _this= this
            axios.defaults.withCredentials = false
            axios.get("http://localhost:8181/index/findAll").then(resp=>{
                console.log(resp)
                _this.tableData = resp.data
            })
        }
    }
</script>
<style>
    body {
        margin: 0;
    }
</style>