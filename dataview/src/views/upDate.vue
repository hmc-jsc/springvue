<template>
    <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="书名">
            <el-input v-model="form.bookname" readOnly style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="概要">
            <el-input v-model="form.context" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="作者">
            <el-input v-model="form.person" style="width: 500px"></el-input>
        </el-form-item>
        <el-form-item label="修改时间">
            <el-input v-model="form.time" v-bind:value="getMyTime()" readOnly style="width: 500px"></el-input>
        </el-form-item>
        <el-button type="primary" @click="onSubmit" style="margin-left: 15%">立即修改</el-button>
    </el-form>
</template>
<script>
    export default {
        data() {
            return {
                form: {
                    bookname:"",
                    context:"",
                    person:"",
                    time:""
                }
            }
        },
        methods: {
            getMyTime(){
                var now = new Date();
                var year = now.getFullYear();       //年
                var month = now.getMonth() + 1;     //月
                var day = now.getDate();            //日
                var hh = now.getHours();            //时
                var mm = now.getMinutes();          //分
                var clock = year + "-";
                if(month < 10)
                    clock += "0";
                clock += month + "-";
                if(day < 10)
                    clock += "0";
                clock += day + " ";
                if(hh < 10)
                    clock += "0";
                clock += hh + ":";
                if (mm < 10) clock += '0';
                clock += mm;
                this.form.time =  clock;
            },
            onSubmit() {
                if (this.form.bookname == ""){
                    alert("未填写");
                    return 0;
                }
                const _this =this
                this.$confirm('是否修改内容, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '修改成功!',
                        options:myData()
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消修改'
                    });
                });
                function myData() {
                    axios.defaults.withCredentials = false;
                    axios.put("http://localhost:8181/index/putData",_this.form).then(resp=>{
                        resp.data == "ok" ? _this.$router.push("/index") :this.$message({
                            type: 'info',
                            message: '已取消修改'
                        });

                    })
                }



            }
        },
        created() {
            this.form.bookname = this.$route.query.bookname
            const _this =this
            axios.defaults.withCredentials = false;
            axios.get("http://localhost:8181/index/getData/"+this.$route.query.bookname).then(resp=>{
                _this.form.context = resp.data.context
                _this.form.person = resp.data.person
            })
        }
    }
</script>