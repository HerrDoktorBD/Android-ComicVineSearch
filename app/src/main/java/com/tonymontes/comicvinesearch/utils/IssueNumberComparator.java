
package com.tonymontes.comicvinesearch.utils;

import com.tonymontes.comicvine.Issue;

import java.util.Comparator;

/*
 * Created by tony on 5/29/17.
 */

public class IssueNumberComparator implements Comparator<Issue> {

    @Override
    public int compare(Issue issue1, Issue issue2) {

        // assume no nulls, and simple ordinal comparisons

        // first by series - stop if this gives a result
        Integer a = issue1.getVolume().getComicvineID();
        Integer b = issue2.getVolume().getComicvineID();

        int seriesResult = a.compareTo(b);
        if (seriesResult != 0)
            return seriesResult;

        // then by issue
        Integer c = Integer.parseInt(issue1.getIssueNumber());
        Integer d = Integer.parseInt(issue2.getIssueNumber());

        return c.compareTo(d);
    }
}

/*
https://stackoverflow.com/questions/6850611/sort-a-list-of-objects-by-multiple-fields

public class PersonComparator implements Comparator<Person>
{
    public int compare(Person p1, Person p2)
    {
        // Assume no nulls, and simple ordinal comparisons

        // First by campus - stop if this gives a result.
        int campusResult = p1.getCampus().compareTo(p2.getCampus());
        if (campusResult != 0)
        {
            return campusResult;
        }

        // Next by faculty
        int facultyResult = p1.getFaculty().compareTo(p2.getFaculty());
        if (facultyResult != 0)
        {
            return facultyResult;
        }

        // Finally by building
        return p1.getBuilding().compareTo(p2.getBuilding());
    }
}
 */

// Basically you're saying, "If I can tell which one comes first just by looking at the campus
// (before they come from different campuses, and the campus is the most important field)
// then I'll just return that result. Otherwise, I'll continue on to compare faculties.
// Again, stop if that's enough to tell them apart. Otherwise, (if the campus and faculty
// are the same for both people) just use the result of comparing them by building."
