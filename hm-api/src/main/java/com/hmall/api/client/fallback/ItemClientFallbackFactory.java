package com.hmall.api.client.fallback;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.cloud.openfeign.FallbackFactory;

import com.hmall.api.client.ItemClient;
import com.hmall.api.dto.ItemDTO;
import com.hmall.api.dto.OrderDetailDTO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
@Override
public ItemClient create(Throwable cause) {
    // log.error("远程调用异常", cause);
    return new ItemClient(){
        @Override
        public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
            log.error("查询商品异常", cause);
            return Collections.emptyList();
        }
        @Override
        public void deductStock(List<OrderDetailDTO> items) {
            throw new RuntimeException(cause);
        }
    };
}
    
}
