package com.sg.springmvc.data;

import com.sg.springmvc.models.Journal;

import java.util.List;

public interface JournalDao {
    Journal getJournalById(int id);
    Journal getJournalByJournalTitle(String journalTitle);
    Journal getJournalByJournalPublisher(String publisher);
    Journal getJournalByJournalLastPublisher(String latest_publisher);
    List<String> getAllJournalTitle();
    List<String> getJournalTitleByFirstLetter(String letter);
    List<Integer> getAllJournalId();
    List<Journal> getAllJournal();

}
