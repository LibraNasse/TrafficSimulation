using System;
using System.Collections;
using System.IO;
namespace SamplePhoneBook
{
	class HashTabFile
	{
		string filename;
		public HashTabFile(string fname)
		{
			filename = fname;
		}

		public Hashtable makeHashtable()
		{
			Hashtable hashtable = new Hashtable();
			StreamReader r = File.OpenText(filename);
			string line = r.ReadLine();
			while (line != null)
			{
				int pos = line.IndexOf(":");
				if (pos >= 0)
				{
					string name = line.Substring(0, pos).Trim();
					long phone = Convert.ToInt64(line.Substring(pos + 1));
					hashtable[name] = phone;
				}

				line = r.ReadLine();
			}

			r.Close();
			return hashtable;
		}
	}
}