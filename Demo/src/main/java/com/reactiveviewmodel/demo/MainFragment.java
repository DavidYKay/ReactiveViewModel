package com.reactiveviewmodel.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.reactiveviewmodel.core.view.EditableTextProperty;
import com.reactiveviewmodel.demo.converter.CharSequenceToStringConverter;
import com.reactiveviewmodel.demo.converter.StringToCharSequenceConverter;
import com.reactiveviewmodel.demo.view.ViewActions;
import com.reactiveviewmodel.demo.viewmodel.MainViewModel;

import rx.subscriptions.CompositeSubscription;

import static com.reactiveviewmodel.core.Binding.twoWayBind;

public class MainFragment extends Fragment {
  private MainViewModel mainViewModel = new MainViewModel();
  private CompositeSubscription viewSubscription;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    viewSubscription = new CompositeSubscription();
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    EditText queryEditText = (EditText) view.findViewById(R.id.queryEditText);
    EditableTextProperty queryText = new EditableTextProperty(queryEditText);
    TextView queryTextView = (TextView) view.findViewById(R.id.queryTextView);

    viewSubscription.add(
        mainViewModel.query
            .whenChanged()
            .subscribe(ViewActions.setText(queryTextView))
    );

    viewSubscription.add(
        twoWayBind(
            mainViewModel.query,
            queryText,
            new StringToCharSequenceConverter(),
            new CharSequenceToStringConverter())
    );
  }

  @Override
  public void onDestroyView() {
    viewSubscription.unsubscribe();
    viewSubscription = null;
    super.onDestroyView();
  }
}
