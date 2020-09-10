package cn.itcast.service.impl;

import cn.itcast.dao.ISysLogDao;
import cn.itcast.domain.SysLog;
import cn.itcast.service.ISysLogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogService implements ISysLogService {
    @Autowired
    private ISysLogDao logDao;

    @Override
    public void save(SysLog log) {

        logDao.save(log);
    }

    @Override
    public List<SysLog> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return logDao.findAll();
    }
}
