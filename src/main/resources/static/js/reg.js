new Vue({
    el:'#app',
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
            loading:false,
            url:'http://localhost/user/register',
            active:0,
            rules: {
                username: [{ validator: validatePass, trigger: "blur" }],
                password: [{ validator: validatePass2, trigger: "blur" }],
              },
            sendData:{
                user:{
                    username:'',
                    password:''
                },
                userInfo:{
                    uid:'1',
                    phone:'',
                    email:'',
                    sex:'',
                    born:''
                }
            },
            file:''
        }
    },
    methods: {
        submitInfo() {
            this.loading=true;
            console.log(this.sendData);
            axios({
                method: 'post',
                url: this.url,
                data: JSON.stringify(this.sendData),
                headers: {
                    'Content-Type': 'application/json;charset=UTF-8'
                }
            }).then(res=>{
                if(res.data["res"]===1) {
                    this.$message({
                        message:res.data["msg"]+" 3秒后跳转...",
                        type:'success',
                        showClose:true,
                        duration:0
                    });
                    setTimeout(function () {
                        window.location.href='http://localhost/';
                    },3000)
                } else {
                    this.loading=false;
                    this.$message.error({
                        message:res.data["msg"],
                        showClose: true,
                        duration: 0
                    });
                }
            }).catch(err=>{
                console.log(err);
            })
        }
    }
})