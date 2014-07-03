using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using MyPriorities.Models;

namespace MyPriorities.Controllers
{
    public class TimeSheetController : ApiController
    {
        BasicMethod bm = new BasicMethod();

        public IEnumerable<TimeSheet> GetTimeSheet(int EmployeeId, string WeekDate)
        {
            return bm.GetTimeSheet(EmployeeId, WeekDate);
        }
    }
}
