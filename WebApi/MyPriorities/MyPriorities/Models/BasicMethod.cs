using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Configuration;
using System.Data;

namespace MyPriorities.Models
{
    public class BasicMethod
    {
        List<Login> lstUser = new List<Login>();        
        List<TimeSheet> lstTimeSheet = new List<TimeSheet>();

        public IEnumerable<Login> CheckUserLogin(string Email, string Password)
        {
            try
            {
                SqlConnection sqlcon = new SqlConnection(ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString);
                string SqlQuery = "Select BadgeID AS UserId, (FirstName +' '+LastName) As UserName FROM dbo.Employees WHERE LTRIM(RTRIM(UPPER(Email))) = UPPER('" + Email + "') AND PassWord = '" + Password + "'";
                SqlDataAdapter da = new SqlDataAdapter(SqlQuery, sqlcon);
                DataTable dtResult = new DataTable();
                da.Fill(dtResult);
                if (dtResult.Rows.Count > 0)
                {
                    foreach (DataRow dr in dtResult.Rows)
                    {
                        lstUser.Add(new Login() { UserId = int.Parse(dr[0].ToString()), UserName = dr[1].ToString(), IsExist = 1 });
                    }
                }
                else
                {
                    lstUser.Add(new Login() { UserId = -1, UserName = string.Empty, IsExist = 0 });
                }
            }
            catch (Exception ex)
            {
                string s = ex.ToString();
            }

            return lstUser;            
        }

        public IEnumerable<TimeSheet> GetTimeSheet(int EmployeeId,string WeekDate)
        {
            try
            {
                SqlConnection sqlcon = new SqlConnection(ConfigurationManager.ConnectionStrings["DefaultConnection"].ConnectionString);
                string SqlQuery = GenerateQuery(EmployeeId, WeekDate);
                SqlDataAdapter da = new SqlDataAdapter(SqlQuery, sqlcon);
                DataTable dtResult = new DataTable();
                da.Fill(dtResult);                
                foreach (DataRow dr in dtResult.Rows)
                {
                    lstTimeSheet.Add(new TimeSheet() { ProjectName = dr["ProjectName"].ToString(), 
                                        Sat = float.Parse(dr["Sat"].ToString()),
                                        Sun = float.Parse(dr["Sun"].ToString()),
                                        Mon = float.Parse(dr["Mon"].ToString()),
                                        Tue = float.Parse(dr["Tue"].ToString()),
                                        Wed = float.Parse(dr["Wed"].ToString()),
                                        Thr = float.Parse(dr["Thr"].ToString()),
                                        Fri = float.Parse(dr["Fri"].ToString()),
                                        Approved = int.Parse(dr["Approved"].ToString()),
                                        Weekdate = DateTime.Parse(dr["WeekDate"].ToString()) });
                }
            }
            
            catch (Exception ex)
            {
                string s = ex.ToString();
            }

            return lstTimeSheet;
        }

        private string GenerateQuery(int EmployeeId, string SelWeekDate)
        {
            string SQLQuery = string.Empty;
            if (SelWeekDate.Equals("-1"))
            {
                SQLQuery = "SELECT prj.ProjectName,wh.Hrs1 AS Sat,wh.Hrs2 AS Sun,wh.Hrs3 AS Mon,wh.Hrs4 AS Tue,wh.Hrs5 AS Wed," +
                                    "wh.Hrs6 AS Thr,wh.Hrs7 AS Fri,CONVERT(INT,wh.Approved) AS Approved ,wh.WeekDate " +
                                    "FROM dbo.WeeklyHours wh INNER JOIN dbo.Employees emp ON wh.BadgeId = emp.BadgeID " +
                                    "INNER JOIN dbo.Projects prj ON wh.ProjectID = prj.ProjectID " +
                                    "WHERE wh.BadgeId = " + EmployeeId + " AND wh.WeekDate = (SELECT MAX(WeekDate) FROM dbo.WeeklyHours WHERE BadgeId = " + EmployeeId + ")" +
                                    "order by wh.WeekDate desc";
            }
            else
            {
                SQLQuery = "SELECT prj.ProjectName,wh.Hrs1 AS Sat,wh.Hrs2 AS Sun,wh.Hrs3 AS Mon,wh.Hrs4 AS Tue,wh.Hrs5 AS Wed," +
                                    "wh.Hrs6 AS Thr,wh.Hrs7 AS Fri,CONVERT(INT,wh.Approved) AS Approved ,wh.WeekDate " +
                                    "FROM dbo.WeeklyHours wh INNER JOIN dbo.Employees emp ON wh.BadgeId = emp.BadgeID " +
                                    "INNER JOIN dbo.Projects prj ON wh.ProjectID = prj.ProjectID " +
                                    "WHERE wh.BadgeId = " + EmployeeId + " AND wh.WeekDate = '" + SelWeekDate + "' " +
                                    "order by wh.WeekDate desc";
            }
            return SQLQuery;
        }
    }
}