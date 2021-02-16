<template>
    <div style="height: 365px">
        <el-container>
            <el-header class="el-header" height="100px" style="margin-bottom: 20px">
                <h3 style="text-align: center">立即注册</h3>
            </el-header>
        </el-container>
    <el-form style="margin:0 auto;width: 60%;" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户名" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="pwd">
            <el-input type="password" v-model="ruleForm.pwd"></el-input>
        </el-form-item>
        <el-form-item label="再次输入密码">
            <el-input type="password" v-model="passpwd"></el-input>
        </el-form-item>
        <el-form-item style="margin-left: 250px">
            <el-button type="primary" @click="submitForm('ruleForm')">立即注册</el-button>
            <el-button @click="resetForm('ruleForm');clearPwd()">重置</el-button>
            <router-link to="login"><el-button style="margin-left: 12px">返回</el-button></router-link>
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
                passpwd:null,
                rules: {
                }
            };
        },
        methods: {
            submitForm(formName) {
                if (this.ruleForm.name==null){
                    alert("请输入用户名")
                    return 0;
                }else if (this.ruleForm.pwd==null){
                    alert("请输入密码")
                    return 0;
                }else if (this.passpwd==null){
                    alert("请重新输入密码")
                    return 0;
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        if (this.ruleForm.pwd != this.passpwd){
                            alert("密码不一致")
                            this.ruleForm.pwd=""
                            this.passpwd=""
                            return 0
                        }
                        const _this =this
                        axios.defaults.withCredentials = false
                        this.ruleForm.pwd = this.$md5(this.ruleForm.pwd )
                        this.ruleForm.pwd = this.$md5(this.ruleForm.pwd )
                        this.ruleForm.pwd = this.$md5(this.ruleForm.pwd )
                        axios.put("http://localhost:8181/login/save",this.ruleForm).then(resp=>{
                            if (resp.data =="yes"){
                                _this.$router.push("login")
                            }else {
                                alert("用户存在！")
                                _this.ruleForm.name=""
                                _this.ruleForm.pwd=""
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
            },
            clearPwd(){
                this.passpwd=""
            }
        }
    }
</script>