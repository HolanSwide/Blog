new Vue({
    el: '#user',
    data: {
        searchText:location.href.slice(location.href.indexOf('?') + 1),
        followed: false,
        noEsssay: true,
        essays: [],
        username: '- 未找到用户 -',
        info: {
            uid: '',
            phone: '',
            sex: '',
            email: '',
            birth: '',
            address: '',
            sign: ''
        },
        me: {
            uid: '',
            username: ''
        },
        postInfo: {
            key: 'uid',
            value: '',
            begin: '0',
            end: '10'
        }
    },
    watch: {
        '$route.path'(now,old){
            // now: 变化之后的数据
            // old: 变化之前的数据
            console.log(now,old);
        },
        searchText(now,old) {
            console.log(now,old);
        }
    },
    created() {
        // 获取初始信息
        let getFromUrl = location.href.slice(location.href.indexOf('?') + 1);
        if (location.href.includes("?")) {
            this.postInfo.value = getFromUrl;
        } else {
            this.postInfo.value = null;
        }

        // 获取本人信息
        axios
            .get('/me')
            .then(response => {
                this.me.username = response.data["user"]["username"];
                this.me.uid = response.data["user"]["uid"];
                if (this.postInfo.value === null) {
                    // 展示的是本人的信息
                    this.postInfo.value = this.me.uid;
                } else {
                    // 判断是否关注
                    axios.get('/usr/isfollow?uid=' + this.me.uid + '&followUid=' + this.postInfo.value)
                        .then(res => {
                            this.followed = res.data["isFollowed"];
                        })
                }
                // 获取页面展示信息
                axios({
                    method: 'post',
                    url: '/info',
                    data: JSON.stringify(this.postInfo),
                    headers: {
                        'Content-Type': 'application/json;charset=UTF-8'
                    }
                }).then(res => {
                    this.info.sign = res.data["data"]["sign"];
                    this.info.email = res.data["data"]["email"];
                    this.info.sex = res.data["data"]["sex"];
                    this.info.birth = res.data["data"]["birth"];
                    this.info.address = res.data["data"]["address"];
                    this.info.uid = res.data["data"]["uid"];
                    this.username = res.data["user"]["username"];
                    // 获取文章
                    axios({
                        method: 'post',
                        url: '/essay',
                        data: JSON.stringify(this.postInfo),
                        headers: {
                            'Content-Type': 'application/json;charset=UTF-8'
                        }
                    }).then(res => {
                        this.essays = res.data["data"];
                        if (this.essays.length !== 0) this.noEsssay = false;
                    });
                });
            });
    },
    methods: {
        // 取消关注
        cancelFollow() {
            axios.delete("/usr/follow", {
                params: {
                    uid: this.me.uid,
                    followUid: this.info.uid
                }
            })
                .then(res => {
                    location.reload();
                })
                .catch(err => {
                    console.error(err);
                });
            this.followed = false;
            location.reload();
        },
        // 关注
        follow() {
            axios.put('/usr/follow?uid=' + this.me.uid + '&followUid=' + this.info.uid
            ).then(res => {
                this.followed = true;
                location.reload();
            })
        }
    }
})