package com.crmsystem.crm.controllers;

import com.crmsystem.crm.entity.Dept;
import com.crmsystem.crm.entity.Emp;
import com.crmsystem.crm.entity.Plan;
import com.crmsystem.crm.service.DeptService;
import com.crmsystem.crm.service.EmpService;
import com.crmsystem.crm.service.PlanService;
import com.crmsystem.crm.util.PageSupport;
import com.crmsystem.crm.util.PlanCondition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: crm
 * @since:JDK-1.8
 * @author: Star-GuoqingLi、郝存凯
 * @create: 2019-11-07 13:47
 * @version:第1版
 * @description:月销售计划表控制器
 **/
@Controller
@RequestMapping("sys")
public class PlanController {

    @Resource
    private PlanService planService;
    @Resource
    private EmpService empService;
    @Resource
    private DeptService deptService;

    //跳转到计划审核表
    @RequestMapping(value = "/plan_info.html")
    public String getPersonPlan(HttpSession session) {
        Emp emp=(Emp)session.getAttribute("session");
        Integer roleId=emp.getRolesId();
        if(roleId==2 || roleId==3) {
            return "sys/information/plan_info";
        }else {
            return "sys/noRight";
        }
    }
    @RequestMapping("/plans.ajax")
    @ResponseBody
    public Object getPlan(@RequestParam(value = "pageIndex",required = true,defaultValue = "1") Integer pageIndex,
                          @RequestParam(value = "pageSize",required = true,defaultValue = "5") Integer pageSize,
                            HttpSession session){
        Emp emp = (Emp)session.getAttribute("session");
        String empCode=emp.getEmpCode();
        List<Plan> planList=planService.findAllPlan(empCode,pageIndex,pageSize);
        int totalCount = planService.findChanCountByCode(empCode);
        PageSupport<Plan> planPage = new PageSupport<>(pageIndex,pageSize,totalCount,planList);
        Map<Object,Object> map = new HashMap<Object,Object>();
        map.put("total",totalCount);
        map.put("data",planList);
        return map;
    }
    //跳转到我的销售计划页面
    @RequestMapping(value = "/person_plan.html")
    public String getPersonPlan(PlanCondition planCondition,
                               @RequestParam(value = "pageIndex",required = true,defaultValue = "0") Integer pageIndex){

        return "sys/emp/person_plan";
    }

    //处理我的销售计划页面的分页检索功能
    @RequestMapping(value = "/plan.ajax")
    @ResponseBody
    public Object doPersonPlan(PlanCondition planCondition,HttpSession session,
                               @RequestParam(value = "pageIndex",required = true,defaultValue = "1") Integer pageIndex,
                               @RequestParam(value = "pageSize",required = true,defaultValue = "5") Integer pageSize){
        PageSupport<Plan> planPage = null;
        if(planCondition==null || "".equals(planCondition)){
            planCondition=new PlanCondition();
        }
        Emp emp = (Emp)session.getAttribute("session");
        planCondition.setEmpCode(emp.getEmpCode());
        int totalCount = planService.planCount(planCondition);
        List<Plan> dataList = planService.findPlanPaging(planCondition,pageIndex,pageSize);
        planPage = new PageSupport<>(pageIndex,pageSize,totalCount,dataList);
        Map<Object,Object> map = new HashMap<Object,Object>();
        map.put("total",totalCount);
        map.put("data",dataList);
        return map;
    }

    //查看我部销售计划的分页检索功能
    @RequestMapping(value = "/deptPlan.ajax",method = RequestMethod.POST)
    @ResponseBody
    public Object doDeptPlan(PlanCondition planCondition,HttpSession session,
                             @RequestParam(value = "pageIndex",required = true,defaultValue = "0") Integer pageIndex,
                             @RequestParam(value = "pageSize",required = true,defaultValue = "5") Integer pageSize){
        if(planCondition==null || "".equals(planCondition)){
            throw new RuntimeException("查找时登录错误！请退出重新登录！");
        }
        if (planCondition.getEmpCode()==null || "".equals(planCondition.getEmpCode())){
            throw new RuntimeException("登录错误！");
        }
        Emp emp = empService.findEmpByCode(planCondition.getEmpCode());
        PlanCondition planCond = new PlanCondition();
        List<Integer> idList = new ArrayList<Integer>();
        if (planCondition.getDeptId()==null || "".equals(planCondition.getDeptId())){
            if(emp.getRolesId()==3){ //如果是销售经理
                idList.add(emp.getDeptId());

            }
            if (emp.getRolesId()==2){
                List<Dept> dept = deptService.findDeptByParentid(emp.getDeptId());
                for (Dept d:dept) {
                    idList.add(d.getDeptId());
                }
            }
        }else{
            idList.add(planCondition.getDeptId());
        }
        planCond.setDeptidList(idList);
        planCond.setPlanMonth(planCondition.getPlanMonth());
        planCond.setPlanYear(planCondition.getPlanYear());
        planCond.setStates(planCondition.getStates());
        Map<Object,Object> map = new HashMap<Object,Object>();
        int total = planService.planCount(planCond);
        List<Plan> planList = planService.findPlanPaging(planCond,pageIndex,pageSize);
        map.put("total",total);
        map.put("data",planList);
        return map;
    }

    //处理添加销售计划的功能
    @RequestMapping(value = "/addPlan.ajax",method = RequestMethod.POST)
    @ResponseBody
    public Object doAddPlan(Plan plan,@RequestParam(value = "rolesId",required = true) Integer rolesId){
        if (plan==null || "".equals(plan) || plan.getEmpCode()==null || "".equals(plan.getEmpCode())){
            throw new RuntimeException("添加销售计划的时候出错！您可能没有把计划创建者封装进去！");
        }
        Emp emp = empService.findEmpSuperior(rolesId,plan.getDeptId());
        plan.setNextCode(emp.getEmpCode());
        int sign = planService.addPlan(plan);
        return sign;
    }
    //处理修改审核计划的状态
    @RequestMapping(value = "/updatePlan.ajax")
    @ResponseBody
    public Object doUpdatePlan(@RequestParam(value = "planId",required = true) Integer planId, @RequestParam(value = "states",required = true) String states){
        int upPlan=planService.updatePlan(planId,states);
        return upPlan;
    }

    //处理修改销售计划的功能
    @RequestMapping(value = "/updPlan.ajax",method = RequestMethod.POST)
    @ResponseBody
    public Object doUpdPlan(Plan plan, HttpSession session){
        Emp emp = (Emp)session.getAttribute("session");
        plan.setEmpCode(emp.getEmpCode());
        if (plan==null || "".equals(plan) || plan.getEmpCode()==null || "".equals(plan.getEmpCode())){
            throw new RuntimeException("修改销售计划的时候出错！您可能没有把计划创建者封装进去！");
        }
        Plan plan1 = planService.findPlanById(plan.getPlanId());
        plan.setNextCode(plan1.getNextCode());
        plan.setPlanYear(plan1.getPlanYear());
        plan.setPlanMonth(plan1.getPlanMonth());
        plan.setDeptId(plan1.getDeptId());
        int sign = planService.updPlan(plan);
        return sign;
    }

    //处理删除销售计划
    @RequestMapping(value = "/delPlan.ajax",method = RequestMethod.POST)
    @ResponseBody
    public Object doDelPlan(@RequestParam(value = "planId",required = true) Integer planId){
        int sign = planService.delPlanById(planId);
        return sign;
    }

    //跳转到查看我部的销售计划的页面的方法
    @RequestMapping(value = "/deptPlan.ajax",method = RequestMethod.GET)
    public String getDeptPlan(){
        return "sys/emp/dept_plan";
    }

    @RequestMapping(value = "findDynPlan.json")
    @ResponseBody
    public Object doDynPlan(PlanCondition planCondition){
        if (planCondition==null ){
            planCondition=new PlanCondition();
        }
        List<Plan> plans = planService.findDynPlan(planCondition);
        return  plans;
    }

}
