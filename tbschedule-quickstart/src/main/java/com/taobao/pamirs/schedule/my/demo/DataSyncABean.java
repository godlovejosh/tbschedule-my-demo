package com.taobao.pamirs.schedule.my.demo;

import com.taobao.pamirs.schedule.IScheduleTaskDealSingle;
import com.taobao.pamirs.schedule.TaskItemDefine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by wuxing
 */
public class DataSyncABean implements IScheduleTaskDealSingle<OrderInfo> {

    public List<OrderInfo> selectTasks(String taskParameter, String ownSign,
                                       int taskItemNum, List<TaskItemDefine> queryCondition,
                                       int eachFetchDataNum) throws Exception {

        List<OrderInfo> result = new ArrayList<OrderInfo>();
        if (queryCondition.size() == 0) {
            return result;
        }

        StringBuffer condition = new StringBuffer();
        for (int i = 0; i < queryCondition.size(); i++) {
            if (i > 0) {
                condition.append(",");
            }
            condition.append(queryCondition.get(i).getTaskItemId());
        }

         /* 场景A：将tbOrder表中的数据分8个任务项，每次取200条数据， 同步到tbOrder_copy表中。 */
        String sql = "select * from tbOrder " + "where "
                + " BillNumber not in (select BillNumber from tbOrder_copy) "
                + " and RIGHT(BuildDate,1) in (" + condition + ") " + "limit "
                + eachFetchDataNum;

        System.out.println("开始执行SQL：" + sql);

//        ResultSet rs = MySQLHelper.executeQuery(sql);
//        while (rs.next()) {
//            OrderInfo order = new OrderInfo();
//            order.BillNumber = rs.getString("BillNumber");
//            order.BuildDate = rs.getString("BuildDate");
//            order.Customer = rs.getString("Customer");
//            order.GoodsName = rs.getString("GoodsName");
//            order.Amount = rs.getFloat("Amount");
//            order.SaleMoney = rs.getFloat("SaleMoney");
//            result.add(order);

//            if (rs.isLast()) {
//                break;
//            }
//        }
//        MySQLHelper.free(rs, rs.getStatement(), rs.getStatement()
//                .getConnection());

        return result;
    }

    public Comparator<OrderInfo> getComparator() {

        return null;
    }

    public boolean execute(OrderInfo task, String ownSign) throws Exception {
//        String sql = "insert into tbOrder_copy values('" + task.BillNumber
//                + "','" + task.BuildDate + "','" + task.Customer + "','"
//                + task.GoodsName + "'," + task.Amount + "," + task.SaleMoney
//                + ")";
//
//        MySQLHelper.executeNonQuery(sql);
//
        System.out.println("execute：");

        return true;
    }
}
