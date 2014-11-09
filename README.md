ReactiveViewModel
=================
### ViewModel
```java
public class MainViewModel {
  public final ReactiveProperty<String> query = new ReactiveProperty<>();

  public MainViewModel() {
  }
}
```

### View
```java
public class ReactiveEditText extends EditText {
  public final ReactiveProperty<CharSequence> text = new ReactiveProperty<>();

  public ReactiveEditText(Context context) {
    super(context);
    init();
  }

  private void init() {
    addTextChangedListener(new SimpleTextWatcher() {
      @Override
      public void afterTextChanged(Editable s) {
        text.set(s);
      }
    });
  }
}
```

### ViewController
```java
public class MainFragment extends ReactiveFragment {
  @Inject MainViewModel mainViewModel;
  @InjectView(R.id.queryEditText) ReactiveEditText queryEditText;
  @InjectView(R.id.queryTextView) TextView queryTextView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setLayout(R.layout.fragment_main);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    addViewSubscription(
        mainViewModel.query
            .whenChanged()
            .subscribe(ViewActions.setText(queryTextView))
    );

    addViewSubscription(
        twoWayBind(
            mainViewModel.query,
            queryEditText.text,
            new StringToCharSequenceConverter(),
            new CharSequenceToStringConverter())
    );
  }
}
```