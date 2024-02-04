package com.xiaolanhe.discover;

import java.util.List;

/**
 * @author xiaolanhe
 * @version V1.0
 * @Package com.xiaolanhe.discover
 * @date 2024/2/4 21:54
 */
public interface LoadBalance {

    /** select one provider */
    String select(List<String> serviceAddresses);
}
