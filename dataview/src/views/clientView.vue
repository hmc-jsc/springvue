<template>
    <div>
        <el-container style="height: 500px; border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu router :default-openeds="['1']">
                    <el-submenu  >
                        <template v-bind:index="1">
                            <template slot="title"><i class="el-icon-setting"></i>数据</template>
                        </template>
                    </el-submenu>
                </el-menu>
            </el-aside>
            <el-container>
                <el-header style="text-align: right;">
                    <el-dropdown>
                        <i class="el-icon-setting"  style="margin-right: 50px"></i>
                        <el-dropdown-menu slot="dropdown">
                            <el-button style="margin: 10px" @click="myExit()">退出登录</el-button>
                        </el-dropdown-menu>
                    </el-dropdown>
                    <span style="font-size: 20px">Hello  {{userName}}</span>
                </el-header>
                <el-main>

                    <el-table
                            :data="tableData"
                            border
                            style="width:593px;">
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
                    </el-table>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    export default {
        methods: {
            myExit(){
                const _this =this
                axios.defaults.withCredentials = true
                axios.get("http://localhost:8181/login/exit/").then(resp=>{
                    console.log(resp.data)
                    _this.$router.push("/login")
                })
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