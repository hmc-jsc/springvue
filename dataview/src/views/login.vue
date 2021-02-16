<template>
    <div style="height: 365px">
    <el-container>
        <el-header class="el-header" height="100px" style="margin-bottom: 20px">
            <h3 style="text-align: center">登录</h3>
        </el-header>
    </el-container>
    <el-form style="margin:0 auto;margin-top:30px;width: 60%;" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pwd">
            <el-input type="password" v-model="ruleForm.pwd"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 27%">
            <el-button type="primary" @click="submitForm('ruleForm')">立即登录</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
            <router-link to="regedit"><el-button style="margin-left: 12px">注册</el-button></router-link>
        </el-form-item>
    </el-form>
    </div>
</template>
<script>
    export default {
        data() {
            return {
                ruleForm: {
                    name:null,
                    pwd:null,
                    crypt:""
                },
                rules: {
                }
            };
        },
        methods: {
            submitForm(formName) {
                if (this.ruleForm.name ==null){
                    alert("输入用户名！")
                    return 0
                }else if (this.ruleForm.pwd == null){
                    alert("输入密码！")
                    return 0
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this =this
                        axios.defaults.withCredentials = false
                        this.ruleForm.pwd = this.$md5(this.ruleForm.pwd )
                        this.ruleForm.pwd = this.$md5(this.ruleForm.pwd )
                        this.ruleForm.pwd = this.$md5(this.ruleForm.pwd )
                        axios.post("http://localhost:8181/login/pass",this.ruleForm).then(resp=>{
                            if (resp.data =="yes"){
                                console.log(resp)
                                _this.$router.replace({
                                    path:"/index",
                                    query:{
                                        myCookie:_this.ruleForm.name
                                    }
                                });
                            }else if(resp.data=="user"){
                                alert("用户名不存在！")
                                _this.ruleForm.name =""
                                _this.ruleForm.pwd =""
                            }else {
                                alert("密码错误")
                                _this.ruleForm.pwd =""
                            }
                        })

                    } else{
                        alert("失败")
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            }
        },
        created() {
            const _this = this
            axios.defaults.withCredentials = true
            axios.get("http://localhost:8181/login/passCookie").then(resp=>{
                if (resp.data=="admin"){
                    console.log(resp)
                    _this.$router.replace("/index");
                }else if (resp.data == "client") {
                    _this.$router.replace("/clientView");
                }
            })
        }
    }
</script>
