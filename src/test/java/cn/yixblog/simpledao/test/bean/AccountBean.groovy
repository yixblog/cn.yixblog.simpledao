package cn.yixblog.simpledao.test.bean

import cn.yixblog.simpledao.annotations.DbColumn
import cn.yixblog.simpledao.annotations.DbTable

/**
 * Created by dyb on 14-1-5.
 */
@DbTable('blog_accounts')
class AccountBean {
    @DbColumn
    int id;
    @DbColumn
    String uid;
    @DbColumn
    String pwd;
    @DbColumn
    String nick;
}
