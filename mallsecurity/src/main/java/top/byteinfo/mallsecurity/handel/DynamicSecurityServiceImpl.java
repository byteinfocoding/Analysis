package top.byteinfo.mallsecurity.handel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.stereotype.Component;
import top.byteinfo.mallsecurity.dao.UmsResourceMapper;
import top.byteinfo.mallsecurity.entity.UmsResource;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Component
public class DynamicSecurityServiceImpl implements DynamicSecurityService{
    @Autowired
    private UmsResourceMapper resourceMapper;
    @Override
    public Map<String, ConfigAttribute> loadDataSource() {
        Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
        List<UmsResource> resourceList = resourceMapper.selectAll();
        for (UmsResource resource : resourceList) {
            map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
        }
        return map;
    }
}
