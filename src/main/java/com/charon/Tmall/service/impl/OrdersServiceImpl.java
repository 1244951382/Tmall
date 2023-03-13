package com.charon.Tmall.service.impl;

import com.charon.Tmall.entity.Orders;
import com.charon.Tmall.mapper.OrdersMapper;
import com.charon.Tmall.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表; 服务实现类
 * </p>
 *
 * @author Charon.Wang
 * @since 2023-02-09
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
