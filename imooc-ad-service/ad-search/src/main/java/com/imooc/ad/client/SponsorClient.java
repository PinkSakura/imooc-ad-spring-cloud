package com.imooc.ad.client;

import com.imooc.ad.client.vo.AdPlan;
import com.imooc.ad.client.vo.AdPlanGetRequest;
import com.imooc.ad.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//实现微服务之间的调用，使用feign
//value是指向我们调用的微服务的名称
//一旦调用过程中发现错误，做一个服务降级fallback，降级是除了熔断以外，Hystrix 的另一个重要功能
//使用 Hystrix 实现降级功能是通过覆写 HystrixCommand 中的 getFallback()
//下面三种情况会导致 Hystrix 执行 Fallback
//主方法抛出异常,主方法执行超时,线程池拒绝,断路器打开
@FeignClient(value = "eureka-client-ad-sponsor",
        fallback = SponsorClientHystrix.class)
public interface SponsorClient {
     //制定需要调用的服务接口，具体调用微服务的URL
    @RequestMapping(value = "/ad-sponsor/get/adPlan",
            method = RequestMethod.POST)
    CommonResponse<List<AdPlan>> getAdPlans(
            @RequestBody AdPlanGetRequest request);
}
