package com.example.merchandiserappjetpackcompose.ui.componenet

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.merchandiserappjetpackcompose.model.Report
import com.example.merchandiserappjetpackcompose.tools.DummyGlobalVariable
import com.example.merchandiserappjetpackcompose.ui.theme.MerchandiserAppJetpackComposeTheme

@Composable
fun ReportItem(
    report: Report,
    onItemSelectAction : (Report) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = report.outletName,
                modifier = Modifier.weight(1f)
            )

            Text(text = "${report.startTime} - ${report.endTime}")
            
            Spacer(modifier = Modifier.padding(horizontal = 8.dp))
            
//            Icon(
//                imageVector = Icons.Default.Edit,
//                contentDescription = null,
//                modifier = Modifier.clickable {
//                    onItemSelectAction(report)
//                }
//            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ReportItemPreview() {
    MerchandiserAppJetpackComposeTheme {
        Surface {
            ReportItem(
                report = DummyGlobalVariable.REPORT_DUMMY,
                onItemSelectAction = {}
            )
        }
    }
}