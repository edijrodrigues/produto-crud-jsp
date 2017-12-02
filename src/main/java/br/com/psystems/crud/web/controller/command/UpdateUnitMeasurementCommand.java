/**
 * 
 */
package br.com.psystems.crud.web.controller.command;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import br.com.psystems.crud.infra.exception.DAOException;
import br.com.psystems.crud.infra.exception.MapperException;
import br.com.psystems.crud.infra.exception.SystemException;
import br.com.psystems.crud.infra.util.Constants;
import br.com.psystems.crud.mapper.impl.UnitMeasurementMapper;
import br.com.psystems.crud.model.BaseEntity;
import br.com.psystems.crud.model.UnitMeasurement;
import br.com.psystems.crud.service.BaseService;
import br.com.psystems.crud.service.UnitMeasurementService;
import br.com.psystems.crud.service.impl.UnitMeasurementServiceImpl;
import br.com.psystems.crud.web.controller.Controllable;
import br.com.psystems.crud.web.controller.Mappable;

/**
 * @author rafael.saldanha
 *
 */
public class UpdateUnitMeasurementCommand extends AbstractCrudCommand<UnitMeasurement> implements Controllable, Mappable {

	public UpdateUnitMeasurementCommand(UnitMeasurementService service) {
		super(service);
	}

	private static final long serialVersionUID = 8169250379905812667L;
	private static Logger logger = Logger.getLogger(UpdateUnitMeasurementCommand.class);
	
	private UnitMeasurementServiceImpl service;

	@Override
	public String execute(HttpServletRequest request) {
		try {
			UnitMeasurement unit = (UnitMeasurement) map(request);
			service.update(unit);
			addSuccessMessage(request, Constants.MESSAGE_UPDATE_SUCCESS);
			
			return Constants.PAGE_UNIT_LIST;
		} catch (DAOException | MapperException | SystemException e) {
			logger.error(e.getMessage());
			setException(request, e);
			return Constants.PAGE_UNIT_FORM;
		}
	}

	@Override
	protected void setService(BaseService<UnitMeasurement> service) {
		this.service = (UnitMeasurementServiceImpl) service;
	}

	@Override
	public BaseEntity map(HttpServletRequest request) throws MapperException {
		return new UnitMeasurementMapper().map(request);
	}
}
