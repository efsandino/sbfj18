package bo.edusoft.sbf18.service.impl;

import bo.edusoft.sbf18.service.ESAuthService;
import org.springframework.stereotype.Service;

@Service
public class ESAuthServiceImpl implements ESAuthService {

	@Override
	public boolean isAdmin(String key) {
		return "admin".equals(key);
	}

}
