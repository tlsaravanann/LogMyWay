using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using MyPriorities.Models;

namespace MyPriorities.Controllers
{
    public class LoginController : ApiController
    {
        BasicMethod bm = new BasicMethod();

        public IEnumerable<Login> GetCheckLogin(string Email,string Password)
        {            
            return bm.CheckUserLogin(Email,Password);
        }
        
    }
}
