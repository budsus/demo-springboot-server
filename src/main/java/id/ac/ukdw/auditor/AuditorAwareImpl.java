package id.ac.ukdw.auditor;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public String getCurrentAuditor() {
		return "RS Panti Rapih";
	}

}
