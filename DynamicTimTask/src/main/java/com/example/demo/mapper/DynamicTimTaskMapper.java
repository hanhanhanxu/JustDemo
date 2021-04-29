package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.DynamicTimTaskEntity;

/**
 * 直接集成mybatisplus的BaseMapper，省去基础增删改查的sql编写
 */
public interface DynamicTimTaskMapper extends BaseMapper<DynamicTimTaskEntity> {
}