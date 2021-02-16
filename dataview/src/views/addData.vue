<template>
<el-form ref="form" :model="form" label-width="80px">
    <el-form-item label="书名">
        <el-input v-model="form.bookname" style="width: 500px"></el-input>
    </el-form-item>
    <el-form-item label="概要" >
        <el-input v-model="form.context" style="width: 500px"></el-input>
    </el-form-item>
    <el-form-item label="作者">
        <el-input v-model="form.person" style="width: 500px"></el-input>
    </el-form-item>
    <el-form-item label="时间">
        <el-input v-model="form.time" v-bind:value="getMyTime()" readOnly style="width: 500px"></el-input>
    </el-form-item>
        <el-button type="primary" @click="onSubmit" style="margin-left: 15%">立即创建</el-button>

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
                const _this = this
                this.$confirm('即将上传数据，请确认, 是否确认?', '提示', {
                    confirmButtonText: '确认',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '上传成功!',
                        options:myData(),
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消上传'
                    });
                });
                function myData() {
                    axios.defaults.withCredentials = false;
                    axios.put("http://localhost:8181/index/saveData",_this.form).then(resp=>{
                        resp.data == "ok" ? _this.$router.push({path:"index"}) : _this.$message({
                            type: 'info',
                            message: '已取消上传'
                        });

                    })
                }
            }
        }
    }
</script>