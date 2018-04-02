package com.xianqu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xianqu.bean.*;
import com.xianqu.bean.order.OrderVo;
import com.xianqu.mapper.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */
@Service
@Transactional
public class OrderService {

    private static final String SALT = "xianqu";

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSupplierMapper orderSupplierMapper;

    @Autowired
    private OrderExpressMapper orderExpressMapper;

    @Autowired
    private OrderPaymentRecordMapper orderPaymentRecordMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private AgentUserMapper agentUserMapper;

    @Autowired
    private UserRecipientsMapper userRecipientsMapper;

    public Long insert(OrderVo orderVo, Long userId) {
        Date now = new Date();
        Order orderDto = new Order();
        if(null != orderVo.getAgent() && orderVo.getAgent() == true) {
            Long pid = userId;
            String password= new SimpleHash("MD5", "12345678", ByteSource.Util.bytes(orderVo.getRecipientsPhone() + SALT),2).toHex();
            User user = new User();
            user.setUsername(orderVo.getRecipientsPhone());
            user.setRealname(orderVo.getRecipients());
            user.setPassword(password);
            user.setCreateDate(now);
            user.setUpdateDate(now);
            user.setIsDelete(false);
            userMapper.insertSelective(user);
            Long normalId = user.getId();
            UserRole userRole = new UserRole();
            userRole.setUid(normalId);
            userRole.setRid(3L);
            userRoleMapper.insert(userRole);
            AgentUser agentUser = new AgentUser();
            agentUser.setUid(normalId);
            agentUser.setPid(pid);
            agentUserMapper.insert(agentUser);
            UserRecipients userRecipients = new UserRecipients();
            userRecipients.setIsDefault(true);
            userRecipients.setUserId(normalId);
            userRecipients.setRecipientsPhone(orderVo.getRecipientsPhone());
            userRecipients.setRecipientsAddress(orderVo.getRecipientsAddress());
            userRecipientsMapper.insert(userRecipients);
            orderVo.setRecipientsId(user.getId());
            orderVo.setStatus("REVIEW");
            orderVo.setUserType("agent");
            orderVo.setAgentId(userId);
        } else{
            orderVo.setStatus("WAIT");
            orderVo.setUserType("dealer");
        }

        orderVo.setCreateUser(userId);
        orderVo.setUpdateUser(userId);
        orderVo.setCreateDate(now);
        orderVo.setUpdateDate(now);
        orderVo.setSource("PC");
        BeanUtils.copyProperties(orderVo, orderDto);
        orderMapper.insertSelective(orderDto);
        Long orderId = orderDto.getId();
        if(null != orderVo.getAgent() && orderVo.getAgent() == true) {
            OrderSupplier supplier = new OrderSupplier();
            supplier.setUserId(1L);
            supplier.setOrderId(orderId);
            supplier.setCreateUser(userId);
            supplier.setUpdateUser(userId);
            supplier.setCreateDate(now);
            supplier.setUpdateDate(now);
            orderSupplierMapper.insert(supplier);
        }

        return orderId;
    }

    public List<OrderVo> getListByUserId(Long userId, Date st, Date et, String payType, String status, String queryKey, String distributor, String agents, Boolean isSupplier) {
        List<OrderVo> list = orderMapper.selectByUserId(userId, st, et,payType, status, queryKey, distributor, agents, isSupplier);
        return list;
    }

    public OrderVo selectByPrimaryKey(Long orderId) {
        OrderVo orderVo = new OrderVo();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        BeanUtils.copyProperties(order, orderVo);
        List<OrderSupplier> suppliers = orderSupplierMapper.selectByOrderId(orderId);
        orderVo.setOrderSupplierList(suppliers);
        return orderVo;
    }

    public void update(OrderVo orderVo, Long userId) {
        Order orderDto = new Order();
        Date now = new Date();
        orderDto.setUpdateUser(userId);
        orderDto.setUpdateDate(now);
        BeanUtils.copyProperties(orderVo, orderDto);
        orderMapper.updateByPrimaryKeySelective(orderDto);
        List<OrderSupplier> suppliers = orderVo.getOrderSupplierList();

        if (null != suppliers && suppliers.size() != 0) {
            orderSupplierMapper.deleteByOrderId(orderVo.getId());
            for(OrderSupplier supplier: suppliers) {
                supplier.setCreateUser(userId);
                supplier.setUpdateUser(userId);
                supplier.setCreateDate(now);
                supplier.setUpdateDate(now);
            }
            orderSupplierMapper.insertByList(suppliers);
        }
    }

    public void delivery(Long userId, Long orderId, String company, String expressOrder) {
        Date now = new Date();
        Order order = new Order();
        order.setId(orderId);
        order.setStatus("DELIVERY");
        order.setUpdateUser(userId);
        order.setUpdateDate(now);
        orderMapper.updateByPrimaryKeySelective(order);
        OrderExpress orderExpress = new OrderExpress();
        orderExpress.setOrderId(orderId);
        orderExpress.setCompany(company);
        orderExpress.setExpressOrder(expressOrder);
        orderExpress.setCreateUser(userId);
        orderExpress.setCreateDate(now);
        orderExpressMapper.insert(orderExpress);
    }

    public OrderExpress deliveryInfo(Long orderId) {
        return orderExpressMapper.selectByOrderId(orderId);
    }

    public void changePayment(Long userId, Long orderId, String payment, String comment) {
        BigDecimal pay = new BigDecimal(payment);
        Order order = new Order();
        Date now = new Date();
        order.setId(orderId);
        order.setUpdateDate(now);
        order.setUpdateUser(userId);
        order.setPayment(pay);
        orderMapper.updateByPrimaryKeySelective(order);
        OrderPaymentRecord orderPaymentRecord = new OrderPaymentRecord();
        orderPaymentRecord.setOrderId(orderId);
        orderPaymentRecord.setComment(comment);
        orderPaymentRecord.setCreateUser(userId);
        orderPaymentRecord.setCreateDate(now);
        orderPaymentRecord.setPayment(pay);
        orderPaymentRecordMapper.insert(orderPaymentRecord);
    }


    public void reviewSuccess(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }
}
