<template>
    <div>
        <el-container style="height: 500px; border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu router :default-openeds="['3']">
                    <el-submenu v-for="(item,index) in $router.options.routes"  v-bind:index="index+''" v-if="item.show">
                        <template>
                            <template slot="title"><i class="el-icon-setting"></i>{{item.name}}</template>
                            <el-menu-item v-for="(item2,index2) in item.children" v-bind:index="item2.path"
                                          :class="$route.path==item2.path?'is-active':''" v-if="item2.show">{{item2.name}}</el-menu-item>
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
                    <router-view></router-view>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>
<style>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
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
                tableData: Array(20).fill(item),
                userName:document.cookie.valueOf().toString().slice(document.cookie.valueOf().toString().indexOf("name=")+5)
            }
        },
        created() {
            const _this = this
            axios.defaults.withCredentials = true
            axios.get("http://localhost:8181/login/passCookie").then(resp=>{
                if (resp.data=="noPass"){
                    console.log(resp)
                    _this.$router.push("/login");
                }else if (resp.data == "client") {
                    _this.$router.push({
                        path:"/clientView",
                    });
                }else if (resp.data =="admin"){
                }
            })
            axios.get("http://localhost:8181/login/cookie/"+this.$route.query.myCookie).then(resp=>{
                console.log(resp.data)
            })
        },
        methods: {
            myExit(){
                const _this =this
                axios.defaults.withCredentials = true
                axios.get("http://localhost:8181/login/exit/").then(resp=>{
                    console.log(resp.data)
                    _this.$router.push("/login")
                })
            }
        }
    };
</script>