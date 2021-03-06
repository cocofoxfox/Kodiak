package com.clearpool.kodiak.feedlibrary.core.cta;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.clearpool.kodiak.feedlibrary.callbacks.IMdSaleListener;
import com.clearpool.kodiak.feedlibrary.core.MdFeed;
import com.clearpool.kodiak.feedlibrary.core.MdLibrary;
import com.clearpool.kodiak.feedlibrary.core.MdLibraryContext;
import com.clearpool.messageobjects.marketdata.MdServiceType;
import com.clearpool.messageobjects.marketdata.Sale;

public class CtsTest implements IMdSaleListener
{
	private static final Logger LOGGER = Logger.getLogger(CtsTest.class.getName());

	public CtsTest() throws Exception
	{
		MdLibraryContext context = new MdLibraryContext(false, 1, false, 0, true);
		MdLibrary ctsLibrary = new MdLibrary(context, MdFeed.CTS, new String[] { "1", "13" }, "127.0.0.1", "127.0.0.1", "C:\\cta");
		ctsLibrary.registerService(MdServiceType.SALE, this);
		ctsLibrary.initProcessors();
		context.start();
	}

	@Override
	public void saleReceived(Sale sale, int channel)
	{
		System.out.println(MdServiceType.SALE + " " + sale);
	}

	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		try
		{
			CtsTest server = new CtsTest();
		}
		catch (Exception e)
		{
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
}