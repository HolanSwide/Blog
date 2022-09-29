new Vue({
    el: '#app',
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请输入用户信息"));
            } else {
                if (this.ruleForm.checkPass !== "") {
                    this.$refs.sendData.user.validateField("password");
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === "") {
                callback(new Error("请输入密码"));
            } else if (value.length < 8 || value.length > 13) {
                callback(new Error("长度必须在8-12之内!"));
            } else {
                callback();
            }
        };
        return {
            loading: false,
            url: {
                user: 'http://localhost/user',
                info: 'http://localhost/info'
            },
            active: 0,
            rules: {
                username: [{validator: validatePass, trigger: "blur"}],
                password: [{validator: validatePass2, trigger: "blur"}],
            },
            sendData: {
                user: {
                    username: '',
                    password: ''
                },
                userInfo: {
                    uid: '1',
                    phone: '',
                    email: '',
                    sex: '',
                    birth: ''
                }
            },
            file: ''
        }
    },
    methods: {
        goReg() {
            this.loading = true;
            axios({
                method: 'put',
                url: this.url.user,
                data: JSON.stringify(this.sendData.user),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then(
                res => {
                    this.loading = false;
                    if (res.data["sign"] === 1) {
                        this.$message({
                            message: res.data["msg"],
                            type: 'success',
                            showClose: true,
                            duration: 3000
                        });
                        this.sendData.userInfo.uid = res.data["data"];
                    } else {
                        this.$message({
                            message: res.data["msg"],
                            type: 'error',
                            showClose: true,
                            duration: 3000
                        });
                    }
                }
            )
        },
        submitInfo() {
            this.loading = true;
            if (this.sendData.userInfo.uid !== 1) {
                axios({
                    method: 'put',
                    url: this.url.info,
                    data: JSON.stringify(this.sendData.userInfo),
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }).then(res => {
                    if (res.data["sign"] === 1) {
                        this.$message({
                            message: res.data["msg"] + " 3秒后跳转...",
                            type: 'success',
                            showClose: true,
                            duration: 3000
                        });
                        setTimeout(function () {
                            window.location.href = 'http://localhost/';
                        }, 3000)
                    } else {
                        this.loading = false;
                        this.$message.error({
                            message: res.data["msg"],
                            showClose: true,
                            duration: 3000
                        });
                    }
                }).catch(err => {
                    console.log(err);
                })
            } else {
                this.$message.errer({
                    message: "账号注册失败，请返回重新注册",
                    showClose:true,
                    duration:3000
                })
            }
        }
    }
})